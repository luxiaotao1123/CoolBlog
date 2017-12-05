import Vue from 'vue'
import Router from 'vue-router'
import store from '../store/index.js'
// import HelloWorld from '@/components/HelloWorld'
// import Login from '@/views/login'
// import CommonLayout from '../layout/admin'
// import blogEdit from '../views/blogEdit'

Vue.use(Router)

// 路由懒加载
const Login = resolve => {
  require.ensure(['../views/Login.vue'], () => {
    resolve(require('../views/Login.vue'))
  })
}
const Blogs = resolve => {
  require.ensure(['@/layout/cool-blog'], () => {
    resolve(require('@/layout/cool-blog'))
  })
}

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'blogs',
      component: Blogs,
      menu: false,
      // redirect: '/',
      children: [
        {
          path: '/home',
          component: function (resolve) {
            require(['../views/home'], resolve)
          },
          name: 'home',
          menu: false,
          meta: {}
        }
      ]
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
      component: function (resolve) {
        require(['../layout/admin'], resolve)
      },
      redirect: '/admin/blogEdit',
      meta: {
        label: '博客管理',
        requiresAuth: true
      },
      children: [
        {
          path: 'blogEdit',
          component: function (resolve) {
            require(['../views/blogEdit'], resolve)
          },
          name: 'blogEdit',
          meta: {
            label: '编辑博客',
            requiresAuth: true
          }
        },
        {
          path: 'blogReEdit/:blogid',
          component: function (resolve) {
            require(['../views/blogReEdit'], resolve)
          },
          name: 'blogReEdit',
          menu: false,
          meta: {
            label: '再编辑博客',
            requiresAuth: true
          }
        },
        {
          path: 'bloglist',
          component: function (resolve) {
            require(['../views/blogList'], resolve)
          },
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
  // console.log(token)
  // console.log(store.state.userid)
  // 判断要去的路由有没有requiresAuth
  if (to.meta.requiresAuth) {
    if (token) {
      next()
      // console.log('有token')
    } else {
      next({
        path: '/login',
        query: { redirect: to.fullPath }  // 将刚刚要去的路由path（却无权限）作为参数，方便登录成功后直接跳转到该路由
      })
    }
  } else {
    next()// 如果无需token,那么随它去吧
    // window.reload()
  }
})

export default router
