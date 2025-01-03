<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="楼名" prop="buildingName">
        <el-input
          v-model="queryParams.buildingName"
          placeholder="请输入楼名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="房间号" prop="roomNumber">
        <el-input
          v-model="queryParams.roomNumber"
          placeholder="请输入房间号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时间" prop="time">
        <el-date-picker clearable
          v-model="queryParams.time"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="维修状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择维修状态" clearable>
          <el-option
            v-for="dict in pmaintain"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['pmaintain:pmaintain:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['pmaintain:pmaintain:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['pmaintain:pmaintain:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['pmaintain:pmaintain:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pmaintainList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="楼名" align="center" prop="buildingName" />
      <el-table-column label="房间号" align="center" prop="roomNumber" />
      <el-table-column label="时间" align="center" prop="time" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.time, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="问题描述" align="center" prop="description" />
      <el-table-column label="维修状态" align="center" prop="status">
        <template #default="scope">
          <dict-tag :options="pmaintain" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['pmaintain:pmaintain:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['pmaintain:pmaintain:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改维修管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="pmaintainRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="楼名" prop="buildingName">
          <el-input v-model="form.buildingName" placeholder="请输入楼名" />
        </el-form-item>
        <el-form-item label="房间号" prop="roomNumber">
          <el-input v-model="form.roomNumber" placeholder="请输入房间号" />
        </el-form-item>
        <el-form-item label="时间" prop="time">
          <el-date-picker clearable
            v-model="form.time"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="问题描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入问题描述" />
        </el-form-item>
        <el-form-item label="维修状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in pmaintain"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Pmaintain">
import { listPmaintain, getPmaintain, delPmaintain, addPmaintain, updatePmaintain } from "@/api/pmaintain/pmaintain";

const { proxy } = getCurrentInstance();
const { pmaintain } = proxy.useDict('pmaintain');

const pmaintainList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    buildingName: null,
    roomNumber: null,
    time: null,
    status: null
  },
  rules: {
    buildingName: [
      { required: true, message: "楼名不能为空", trigger: "blur" }
    ],
    roomNumber: [
      { required: true, message: "房间号不能为空", trigger: "blur" }
    ],
    time: [
      { required: true, message: "时间不能为空", trigger: "blur" }
    ],
    description: [
      { required: true, message: "问题描述不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "维修状态不能为空", trigger: "change" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询维修管理列表 */
function getList() {
  loading.value = true;
  listPmaintain(queryParams.value).then(response => {
    pmaintainList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    buildingName: null,
    roomNumber: null,
    time: null,
    description: null,
    status: null
  };
  proxy.resetForm("pmaintainRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加维修管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getPmaintain(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改维修管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["pmaintainRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updatePmaintain(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPmaintain(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除维修管理编号为"' + _ids + '"的数据项？').then(function() {
    return delPmaintain(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('pmaintain/pmaintain/export', {
    ...queryParams.value
  }, `pmaintain_${new Date().getTime()}.xlsx`)
}

getList();
</script>
