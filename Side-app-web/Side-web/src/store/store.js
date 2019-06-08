/**
 * Created by gmc on 11/3/19.
 */
import Vuex from 'vuex'
import Vue from 'vue'
import * as types from './types'

Vue.use(Vuex);
export default new Vuex.Store({
    state: {
        user: {},
        token: null,
        title: '',
        operation: {
        		add: '1',
        		edit: '2',
        		del: '3',
        		query: '0',
        		imp: '4',
        		exp: '5'
        },
        roleAuthorization: {
        		roleId: null,
        		menuId: null,
        		operation: null,
        		authorizationId: null
        },
        authorizationList: []
    },
    mutations: {
        [types.LOGIN]: (state, data) => {
            localStorage.token = data;
            state.token = data;
        },
        [types.LOGOUT]: (state) => {
            localStorage.removeItem('token');
            state.token = null
        },
        [types.TITLE]: (state, data) => {
            state.title = data;
        }
    }
})