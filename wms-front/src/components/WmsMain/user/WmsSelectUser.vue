<template>
    <div>
        <div style="margin-bottom: 1%;" align="left">
            <el-input v-model="name" 
                size="small" placeholder="请输入姓名" 
                suffix-icon="el-icon-search" style="width: 15%;" 
                @keyup.enter.native="loadPost">
            </el-input>
            <el-select v-model="sex" filterable placeholder="请选择性别" 
                size="small" style="margin-left: 5px; width: 15%">
                <el-option
                    v-for="item in sexes" 
                    :key="item.value" 
                    :label="item.label" 
                    :value="item.value">
                </el-option>
            </el-select>
            <el-button size="mini" type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
            <el-button size="mini" type="success" style="margin-left: 5px" @click="resetParam">重置</el-button>
        </div>
        <el-table :data="tableData"
            :header-cell-style="{background:'#ffcc99', color:'#555'}"
            highlight-current-row
            @current-change="selectCurrentChange">
            <el-table-column prop="id" label="ID" min-width="8%">
            </el-table-column>
            <el-table-column prop="no" label="账号" min-width="12%">
            </el-table-column>
            <el-table-column prop="name" label="姓名" min-width="12%">
            </el-table-column>
            <el-table-column prop="sex" label="性别" min-width="8%">
                <template slot-scope="scope">
                    <el-tag
                        :type="scope.row.sex === 1 ? 'primary' : 'success' "
                        disable-transition>{{ scope.row.sex === 1 ? '男' : '女' }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="age" label="年龄" min-width="8%">
            </el-table-column>
            <el-table-column prop="phone" label="电话" min-width="18%">
            </el-table-column>
            <el-table-column prop="roleId" label="角色" min-width="12%">
                <template slot-scope="scope">
                    <el-tag
                        :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success') "
                        disable-transition>{{ scope.row.roleId === 0 ? '超级管理员' : (scope.row.roleId === 1 ? '管理员' : '用户') }}</el-tag>
                </template>
            </el-table-column>
        </el-table>
        
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[1, 2, 5, 10]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
    </div>
</template>

<script>
export default {
    name: "WmsSelectUser",
    data() {
        return {
            tableData: [],
            pageSize: 5,
            pageNum: 1,
            total: 0,
            name: '',
            sex: '',
            sexes: [
                {
                    value: '1',
                    label: '男'
                },
                {
                    value: '0',
                    label: '女'
                }
            ]
        }
    },
    methods: {
        loadGet() {
            this.$axios.get(this.$httpUrl + '/user/list').then(res=>res.data).then(res=>{
                console.log(res);
            })
        },

        loadPost() {
            this.$axios.post(this.$httpUrl + '/user/listPage', {
                pageSize: this.pageSize,
                pageNum: this.pageNum,
                param:{
                    name: this.name,
                    sex: this.sex,
                    roleId: '2'
                }
            }).then(res=>res.data).then(res=>{
                console.log(res.code);
                if(res.code==200) {
                    this.tableData = res.data;
                    this.total = res.total;
                } else {
                    alert("获取数据失败");
                }
            })
        },

        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.pageNum = 1;
            this.pageSize = val;
            this.loadPost();
        },

        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.pageNum = val;
            this.loadPost();
        },

        resetParam() {
            this.name='';
            this.sex='';
        },

        selectCurrentChange(val) {
            this.currentRow = val;
        }

    },

    beforeMount() {
        this.loadPost();
    }
}
</script>

<style scoped>

</style>