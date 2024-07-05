import VueRouter from "vue-router";
import WmsLogin from "../WmsLogin.vue";
import WmsIndex from "../WmsIndex.vue";

const routes = [
    {
        path: '/',
        name: 'login',
        component: WmsLogin
    },
    {
        path: '/WmsIndex',
        name: 'index',
        component: WmsIndex
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router;