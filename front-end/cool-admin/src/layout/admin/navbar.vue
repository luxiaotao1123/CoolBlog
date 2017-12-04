<template>
<section class="app-navbar">
  <Col span="8">
     <Menu :active-name="active.item" :open-names="[active.sub]">
         <Submenu  v-for="(item, index) in list" :index="item.path" :key="item.id" :name='item.path'>
             <template slot="title" >
                        <Icon type="ios-paper"></Icon>
                        {{item.label}}
            </template>
             <!-- <router-link :to="i.path"> -->
             <MenuItem v-for="(i, subIndex) in item.list" :index="i.path" :key="'/admin/'+i.path" :name="'/admin/'+i.path"  @click.native="go('/admin/'+i.path)">
               {{i.label}}
             <!-- </router-link> -->
             </MenuItem>
         </Submenu>
     </Menu>
     </Col>
</section>
</template>

<script>
import routes from '@/router/index.js'
export default {
  data () {
    return {
      list: [],
      active: {
        sub: '',
        item: ''
      }
    }
  },
  computed: {
    openedIndexList () {
      let len = this.list.length || 0
      let indexList = []
      for (let i = 0; i < len; i++) {
        indexList.push(this.list[i].path)
      }
      return indexList
    },
    navHeight () {
      return window.innerHeight - 68 - 48 + 'px'
    }
  },
  methods: {
    initMenu () {
      let routeli = routes.options.routes
      let routelist = routeli
      // let routelist = routelist.push(routerlist1)
      // console.log(routelist)
      // console.log(this.$route.path)
      this.active.item = this.$route.path
      let list = []
      routelist.forEach(item => {
        if (item.menu === false || !item.name) {
          return
        }
        let children = item.children || []
        let childList = []
        children.forEach(child => {
          if (!child.name || child.menu === false) {
            return
          }
          childList.push({
            label: (child.meta && child.meta.label) || child.name,
            value: child.name,
            path: child.path,
            icon: child.icon || ''
          })
        })
        let menuItem = {
          show: true,
          label: (item.meta && item.meta.label) || item.name,
          icon: item.icon,
          path: item.path,
          list: childList
        }
        list.push(menuItem)
      })
      this.list = list
      this.active.sub = list[0].path
    },
    go (name) {
      // console.log(name)
      this.$router.push(name)
    }
  },
  created () {
    this.initMenu()
  }
}
</script>
<style scoped>
.app-navbar {
  z-index: 9999;
}
.nav-box {
  overflow: auto;
}
.n3-dropdown-menu > li {
  padding: 4px 12px !important;
}
</style>
