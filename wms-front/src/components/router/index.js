import VueRouter from "vue-router";
import WmsLogin from "../WmsLogin.vue";
import WmsIndex from "../WmsIndex.vue";
import WmsHome from "../WmsMain/WmsHome.vue";
import WmsMain from "../WmsMain/WmsMain.vue";
import WmsAdminManage from "../WmsMain/WmsAdminManage.vue";
import WmsUserManage from "../WmsMain/WmsUserManage.vue";

const routes = [
    {
        path: '/',
        name: 'login',
        component: WmsLogin
    },
    {
        path: '/main',
        component: WmsIndex,
        children: [
            {
                path: '/',
                name: 'index',
                meta: {
                    title: "首页"
                },
                component: WmsMain,
            },
            {
                path: 'home',
                name: 'home',
                meta: {
                    title: "人员管理"
                },
                component: WmsHome,
            },
            {
                path: 'admin',
                name: 'admin',
                meta: {
                    title: "管理员管理"
                },
                component: WmsAdminManage,
            },
            {
                path: 'user',
                name: 'user',
                meta: {
                    title: "用户管理"
                },
                component: WmsUserManage,
            }
        ]
    }
];

const router = new VueRouter({
    mode: 'history',
    routes
});
const VueRouterPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err=>err)
};

export default router;