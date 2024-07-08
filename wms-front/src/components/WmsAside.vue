<template>
    <el-menu 
        style="height: 500%;" 
        default-active="/Home" 
        active-text-color="darkorange" 
        :collapse="isCollapse" 
        :collapse-transition="false"
        router
    >
        <el-menu-item index="/main">
            <i class="el-icon-house"></i>
            <span slot="title">首页</span>
        </el-menu-item>
        <!-- 这里注意后端传来的menu数组中menu对象的field名在此变为了小写 -->
        <el-menu-item :index="'/main/'+item.menuclick" v-for="(item,i) in menu" :key="i">
            <i :class="item.menuicon"></i>
            <span slot="title">{{ item.menuname }}</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
export default {
    name: "WmsAside", 
    data() {
        return {

        }
    },
    computed: {
        "menu": {
            get() {
                return this.$store.state.menu;
            }
        }
    },
    props: {
        isCollapse: Boolean
    },
}
</script>

<style scoped>

</style>