import VueRouter from "vue-router";
import WmsLogin from "../WmsLogin.vue";
import WmsIndex from "../WmsIndex.vue";
import WmsHome from "../WmsHome.vue";

const routes = [
    {
        path: '/',
        name: 'login',
        component: WmsLogin
    },
    {
        path: '/WmsIndex',
        name: 'index',
        component: WmsIndex,
        children: [
            {
                path: '/WmsHome',
                name: 'home',
                meta: {
                    title: "首页"
                },
                component: WmsHome,
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router;