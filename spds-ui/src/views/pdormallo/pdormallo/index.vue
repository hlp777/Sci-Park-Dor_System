<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
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
      <el-form-item label="入住时间" prop="checkinTime">
        <el-date-picker clearable
          v-model="queryParams.checkinTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择入住时间">
        </el-date-picker>
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
          v-hasPermi="['pdormallo:pdormallo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['pdormallo:pdormallo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['pdormallo:pdormallo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['pdormallo:pdormallo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pdormalloList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="用户名" align="center" prop="userName" />
      <el-table-column label="楼名" align="center" prop="buildingName" />
      <el-table-column label="房间号" align="center" prop="roomNumber" />
      <el-table-column label="入住时间" align="center" prop="checkinTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.checkinTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['pdormallo:pdormallo:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['pdormallo:pdormallo:remove']">删除</el-button>
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

    <!-- 添加或修改宿舍分配对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="pdormalloRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="userName" v-if="!isEdit">
          <el-input v-model="form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="楼名" prop="buildingName">
          <el-input v-model="form.buildingName" placeholder="请输入楼名" />
        </el-form-item>
        <el-form-item label="房间号" prop="roomNumber">
          <el-input v-model="form.roomNumber" placeholder="请输入房间号" />
        </el-form-item>
        <el-form-item label="入住时间" prop="checkinTime">
          <el-date-picker clearable
            v-model="form.checkinTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择入住时间">
          </el-date-picker>
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

<script setup name="Pdormallo">
import { listPdormallo, getPdormallo, delPdormallo, addPdormallo, updatePdormallo } from "@/api/pdormallo/pdormallo";

const { proxy } = getCurrentInstance();

const pdormalloList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const isEdit = ref(false);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userName: null,
    buildingName: null,
    roomNumber: null,
    checkinTime: null
  },
  rules: {
    userName: [
      { required: true, message: "用户名不能为空", trigger: "blur" }
    ],
    buildingName: [
      { required: true, message: "楼名不能为空", trigger: "blur" }
    ],
    roomNumber: [
      { required: true, message: "房间号不能为空", trigger: "blur" }
    ],
    checkinTime: [
      { required: true, message: "入住时间不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询宿舍分配列表 */
function getList() {
  loading.value = true;
  listPdormallo(queryParams.value).then(response => {
    pdormalloList.value = response.rows;
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
    userName: null,
    buildingName: null,
    roomNumber: null,
    checkinTime: null
  };
  proxy.resetForm("pdormalloRef");
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
  isEdit.value = false;
  reset();
  open.value = true;
  title.value = "添加宿舍分配";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  isEdit.value = true;
  reset();
  const _id = row.id || ids.value
  getPdormallo(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改宿舍分配";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["pdormalloRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updatePdormallo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPdormallo(form.value).then(response => {
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
  const username = row.userName;
  proxy.$modal.confirm('是否确认删除用户名为"' + username + '"的数据项？').then(function() {
    return delPdormallo(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('pdormallo/pdormallo/export', {
    ...queryParams.value
  }, `pdormallo_${new Date().getTime()}.xlsx`)
}

getList();
</script>
