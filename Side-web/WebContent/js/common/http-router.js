/*
 * 前端页面转发路由。
 * 控制前端页面跳转地址
 * */

import Vue from './js/vue/vue';
import VueRouter from './js/vue-router/vue-router';

Vue.use(VueRouter)

//配置路由地址
const routes = [
    {
        path: '/',
        name: '/',
        component: Index
    },
    {
        path: '/repository',
        name: 'repository',
        meta: {
            requireAuth: true,
        },
        component: Repository
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    }
];

// 页面刷新时，重新赋值token
if (window.localStorage.getItem('token')) {
    store.commit(types.LOGIN, window.localStorage.getItem('token'))
}

const router = new VueRouter({
    routes
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(r => r.meta.requireAuth)) {
        if (store.state.token) {
            next();
        }
        else {
            next({
                path: '/login',
                query: {redirect: to.fullPath}
            })
        }
    }
    else {
        next();
    }
})

export default router;
