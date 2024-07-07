<template>
    <div style="display: flex; line-height: 400%;">
        
        <div>
            <i :class="foldIcon" style="font-size: 200%; cursor: pointer;" @click="collapse"></i>
        </div>
        
        <div style="flex: 1; text-align: center; font-size: 220%;">
            <span>1918# Warehouse M.S.</span>
        </div>
       
        <span style="font-size: 150%;">{{user.name}}</span>
        
        <el-dropdown><i class="el-icon-arrow-down" style="margin-left: 80%; font-size: 100%;"></i>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="userCenter">
                    <i class="el-icon-user"></i>个人中心</el-dropdown-item>
                <el-dropdown-item @click.native="userExit">
                    <i class="el-icon-switch-button"></i>退出</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

<script>
export default {
    name: "WmsHeader",
    data() {
        return {
            user: JSON.parse(sessionStorage.getItem('CurUser'))
        }
    },
    props: {
        foldIcon: String
    },
    methods: {
        userCenter() {
            console.log('to_home');
            this.$router.push("/index/home");
        },

        userExit() {
            this.$confirm('确定退出？', '⚠️', {
                confirmButtonText: '确定',
                type: 'warning',
                center: true
            }).then(()=>{
                this.$message({
                    type: 'success',
                    message: '退出登录成功'
                })
                console.log('log_out');
                this.$router.push("/");
                sessionStorage.clear();
            }).catch(()=>{
                this.$message({
                type: 'info',
                message: '取消退出登录'
                })
            })
        },

        collapse() {
            this.$emit('doCollapse');
        },

        created() {
        }
    }
    
}
</script>

<style scoped>

</style>