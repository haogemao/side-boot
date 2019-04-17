import Vue from 'vue'
import Router from 'vue-router'
//import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import Main from '@/components/MainPage'
import MenuPage from '@/components/childs/MenuPage'
import store from '../store/store'
import * as types from '../store/types'


Vue.use(Router)

//配置路由地址
const routes = [
    {
        path: '/',
        name: 'Login',
        component: Login
    },
    {
    		path: '/main',
    		name: 'main',
    		component: Main,
    		children : [
	    		{
	    			path: 'menus',
	    			name: 'menus',
    				component : MenuPage
	    		}
    		]
    }
];

// 页面刷新时，重新赋值token
if (window.localStorage.getItem('token')) {
    store.commit(types.LOGIN, window.localStorage.getItem('token'))
}

const router = new Router({
	mode:'history',
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
});

export default router;

