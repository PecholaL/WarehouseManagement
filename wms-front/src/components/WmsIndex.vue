<template>
    <el-container style="height: 100%; border: 1px solid #eee">
        
        <el-aside :width="asideWidth" style="background-color: rgb(238, 241, 246); overflow: hidden;">
            <WmsAside :isCollapse="isCollapse"></WmsAside>
        </el-aside>

        <el-container class="content">
            <el-header style="text-align: right; font-size: 60%; height: 40px; border-bottom: darkorange 1px solid;">
                <WmsHeader @doCollapse="doCollapse" :foldIcon="foldIcon"></WmsHeader>
            </el-header>

            <el-main style="height: 100%;">
                <router-view/>
            </el-main>
        </el-container>
</el-container>
</template>

<style>
    .el-header {
        /* background-color: #daa56e; */
        color: #000000;
        line-height: 60px;
    }

    .el-main{
        padding: 5px;
    }

    .el-aside {
        color: #333;
    }

    /* 做出Header和Aside固定、Main可以滚动的效果 */
    .content {
        height: calc(100vh - 60px);
    }
</style>

<script>
import WmsAside from "./WmsAside.vue";
import WmsHeader from "./WmsHeader.vue";

export default {
    name: "WmsIndex",
    components: {WmsAside,WmsHeader},
    data() {
        return {
            isCollapse: false,
            asideWidth: '15%',
            foldIcon: 'el-icon-s-fold'
        }
    },
    methods: {
        doCollapse() {
            this.isCollapse = !this.isCollapse;
            if(!this.isCollapse) {
                this.asideWidth = '15%';
                this.foldIcon = 'el-icon-s-fold';
            } else {
                this.asideWidth = '4.5%';
                this.foldIcon = 'el-icon-s-unfold';
            }
        }
    }
};
</script>