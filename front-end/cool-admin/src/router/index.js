import Vue from 'vue'
import Router from 'vue-router'
import store from '../store/index.js'
import HelloWorld from '@/components/HelloWorld'
// import Login from '@/views/login'
import CommonLayout from '../layout'
import blogEdit from '../views/blogEdit'
import blogList from '../views/blogList'

Vue.use(Router)

// 路由懒加载
const Login = resolve => {
  require.ensure(['../views/Login.vue'], () => {
    resolve(require('../views/Login.vue'))
  })
}

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld,
      menu: false
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      menu: false
    },
    {
      path: '/admin',
      name: '博客管理',
      icon: 'bars',
      component: CommonLayout,
      redirect: '/admin/blogEdit',
      meta: {
        label: '博客管理',
        requiresAuth: true
      },
      children: [
        {
          path: 'blogEdit',
          component: blogEdit,
          name: 'blogEdit',
          meta: {
            label: '编辑博客',
            requiresAuth: true
          }
        },
        {
          path: 'bloglist',
          component: blogList,
          name: 'blogList',
          meta: {
            label: '博客列表',
            requiresAuth: true
          }
        }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  // 获取store里面的token
  let token = store.state.token
  console.log(token)
  console.log(store.state.userid)
  // 判断要去的路由有没有requiresAuth
  if (to.meta.requiresAuth) {
    if (token) {
      next()
      console.log('有token')
    } else {
      next({
        path: '/login',
        query: { redirect: to.fullPath }  // 将刚刚要去的路由path（却无权限）作为参数，方便登录成功后直接跳转到该路由
      })
    }
  } else {
    next()// 如果无需token,那么随它去吧
  }
})

export default router
