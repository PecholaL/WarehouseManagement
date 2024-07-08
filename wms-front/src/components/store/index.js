import Vue from "vue";
import Vuex from "vuex";
// import router from "../router"
// import WmsIndex from "../WmsIndex.vue";
// import WmsHome from "../WmsMain/WmsHome.vue";
// import WmsMain from "../WmsMain/WmsMain.vue";
// import WmsAdminManage from "../WmsMain/WmsAdminManage.vue";
// import WmsUserManage from "../WmsMain/WmsUserManage.vue";
Vue.use(Vuex);

// function addNewRoute(menuList) {
//     console.log(menuList);
//     let routes = router.options.routes;
//     console.log(routes);
    // {
    //     path: '/main',
    //     component: WmsIndex,
    //     children: [
    //         {
    //             path: '/',
    //             name: 'index',
    //             meta: {
    //                 title: "首页"
    //             },
    //             component: WmsMain,
    //         },
    //         {
    //             path: 'home',
    //             name: 'home',
    //             meta: {
    //                 title: "人员管理"
    //             },
    //             component: WmsHome,
    //         },
    //         {
    //             path: 'admin',
    //             name: 'admin',
    //             meta: {
    //                 title: "管理员管理"
    //             },
    //             component: WmsAdminManage,
    //         },
    //         {
    //             path: 'user',
    //             name: 'user',
    //             meta: {
    //                 title: "用户管理"
    //             },
    //             component: WmsUserManage,
    //         }
    //     ]
    // }
// }


// export default new Vuex.Store({
const store = new Vuex.Store({
    state: {
        menu: []
    },
    mutations: {
        setMenu(state, menuList) {
            state.menu = menuList;
            // addNewRoute(menuList);
        }
    },
    getters: {
        getMenu(state) {
            return state.menu;
        }
    }
});

export default store;
