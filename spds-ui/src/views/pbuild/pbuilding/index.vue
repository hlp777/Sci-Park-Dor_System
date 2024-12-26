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
      <el-form-item label="地址" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入地址"
          clearable
          @keyup.enter="handleQuery"
        />
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
          v-hasPermi="['pbuild:pbuilding:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['pbuild:pbuilding:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['pbuild:pbuilding:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['pbuild:pbuilding:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pbuildingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="buildingId" />
      <el-table-column label="楼名" align="center" prop="buildingName" />
      <el-table-column label="地址" align="center" prop="location" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['pbuild:pbuilding:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['pbuild:pbuilding:remove']">删除</el-button>
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

    <!-- 添加或修改楼层管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="pbuildingRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="楼名" prop="buildingName">
          <el-input v-model="form.buildingName" placeholder="请输入楼名" />
        </el-form-item>
        <el-form-item label="地址" prop="location">
          <el-input v-model="form.location" placeholder="请输入地址" />
        </el-form-item>
        <el-divider content-position="center">房间管理信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="Plus" @click="handleAddProom">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="Delete" @click="handleDeleteProom">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="proomList" :row-class-name="rowProomIndex" @selection-change="handleProomSelectionChange" ref="proom">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="房间号" prop="roomNumber" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.roomNumber" placeholder="请输入房间号" />
            </template>
          </el-table-column>
          <el-table-column label="容量" prop="capacity" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.capacity" placeholder="请输入容量" />
            </template>
          </el-table-column>
          <el-table-column label="状态" prop="status" width="150">
            <template #default="scope">
              <el-select v-model="scope.row.status" placeholder="请选择状态">
                <el-option
                  v-for="dict in sys_job_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
        </el-table>
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

<script setup name="Pbuilding">
import { listPbuilding, getPbuilding, delPbuilding, addPbuilding, updatePbuilding } from "@/api/pbuild/pbuilding";

const { proxy } = getCurrentInstance();
const { sys_job_status } = proxy.useDict('sys_job_status');

const pbuildingList = ref([]);
const proomList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const checkedProom = ref([]);
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
    location: null
  },
  rules: {
    buildingName: [
      { required: true, message: "楼名不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询楼层管理列表 */
function getList() {
  loading.value = true;
  listPbuilding(queryParams.value).then(response => {
    pbuildingList.value = response.rows;
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
    buildingId: null,
    buildingName: null,
    location: null
  };
  proomList.value = [];
  proxy.resetForm("pbuildingRef");
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
  ids.value = selection.map(item => item.buildingId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加楼层管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _buildingId = row.buildingId || ids.value
  getPbuilding(_buildingId).then(response => {
    form.value = response.data;
    proomList.value = response.data.proomList;
    open.value = true;
    title.value = "修改楼层管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["pbuildingRef"].validate(valid => {
    if (valid) {
      form.value.proomList = proomList.value;
      if (form.value.buildingId != null) {
        updatePbuilding(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPbuilding(form.value).then(response => {
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
  const _buildingIds = row.buildingId || ids.value;
  proxy.$modal.confirm('是否确认删除楼层管理编号为"' + _buildingIds + '"的数据项？').then(function() {
    return delPbuilding(_buildingIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 房间管理序号 */
function rowProomIndex({ row, rowIndex }) {
  row.index = rowIndex + 1;
}

/** 房间管理添加按钮操作 */
function handleAddProom() {
  let obj = {};
  obj.roomNumber = "";
  obj.capacity = "";
  obj.status = "";
  proomList.value.push(obj);
}

/** 房间管理删除按钮操作 */
function handleDeleteProom() {
  if (checkedProom.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的房间管理数据");
  } else {
    const prooms = proomList.value;
    const checkedProoms = checkedProom.value;
    proomList.value = prooms.filter(function(item) {
      return checkedProoms.indexOf(item.index) == -1
    });
  }
}

/** 复选框选中数据 */
function handleProomSelectionChange(selection) {
  checkedProom.value = selection.map(item => item.index)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('pbuild/pbuilding/export', {
    ...queryParams.value
  }, `pbuilding_${new Date().getTime()}.xlsx`)
}

getList();
</script>
