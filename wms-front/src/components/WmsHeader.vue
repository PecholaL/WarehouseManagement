<template>
    <div style="display: flex; line-height: 400%;">
        
        <div>
            <i :class="foldIcon" style="font-size: 200%; cursor: pointer;" @click="collapse"></i>
        </div>
        
        <div style="flex: 1; text-align: center; font-size: 220%;">
            <span>1918# Warehouse M.S.</span>
        </div>
       
        <span style="font-size: 150%;">Pechola</span>
        
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
    props: {
        foldIcon: String
    },
    methods: {
        userCenter() {
            console.log('to_user');
        },
        userExit() {
            console.log('log_out');
        },
        collapse() {
            this.$emit('doCollapse');
        }
    }
    
}
</script>

<style scoped>

</style>