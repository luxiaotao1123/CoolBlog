<template>
   <div>
        <Table border ref="selection" :columns="columns4" :data="data1" @on-select='selectItem'></Table>
         <div style="margin-top:15px;margin-right:30px;" align="right">
        <Page :total="pageData.total" :page-size='pageData.pageSize' :current.sync="pageData.pageNum" @on-change ='changePage'></Page>
         </div>
        <Button @click="handleSelectAll(true)">全选</Button>
        <Button @click="handleSelectAll(false)">取消</Button>
    </div>
</template>
<script>
import service from '../utils/service.js'
export default {
  data () {
    return {
      columns4: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '标题',
          key: 'title'
        },
        {
          title: '标签',
          key: 'label'
          // width: 50
        },
        {
          title: '摘要',
          key: 'abstract'
        },
        {
          title: '更新时间',
          key: 'updatetime'
        }
      ],
      data1: [],
      pageData: {}
    }
  },
  methods: {
    handleSelectAll (status) {
      this.$refs.selection.selectAll(status)
    },
    initList () {
      let list = []
      let that = this
      let pageData = this.pageData
      service.get('/api/admin/blogs', {
        pageData
      })
      .then(function (responce) {
        console.log(responce.data)
        // 分页
        that.pageData.pageSize = Number(responce.data.blog.pageSize)
        that.pageData.pageNum = Number(responce.data.blog.pageNum)
        that.pageData.total = Number(responce.data.blog.total)
        that.pageData.pages = Number(responce.data.blog.pages)
        let orglist = responce.data.blog.list
        orglist.forEach(item => {
          list.push({
            // title: '标题',
            id: item.blogid,
            title: item.title,
            label: item.label,
            abstract: item.content.substring(4, 10),
            updatetime: item.updatetime
          })
        })
        console.log(list)
        that.data1 = list
      })
    },
    changePage (val) {
      console.log(val)
      this.pageData.pageNum = val
      this.filterData()
    },
    filterData (fData) {
      let data = {}
      let pageNum = this.pageData.pageNum
      let list = []
      let that = this
      console.log(data)
      service.get('/api/admin/blogs', {
        params: {
          pageNum: pageNum
        }
      })
      .then(function (responce) {
        console.log('change!')
        // 分页
        // that.pageData.pageSize = Number(responce.data.blog.pageSize)
        that.pageData.pageNum = Number(responce.data.blog.pageNum)
        // that.pageData.pages = Number(responce.data.blog.pages)
        let orglist = responce.data.blog.list
        orglist.forEach(item => {
          list.push({
            // title: '标题',
            title: item.title,
            label: item.label,
            abstract: item.content.substring(4, 10),
            updatetime: item.updatetime
          })
        })
        // console.log(list)
        that.data1 = list
      })
    },
    selectItem (item) {
      console.log(item)
      let sel = []
      for (let index = 0; index < item.length; index++) {
        const element = item[index].id
        sel.push(element)
      }
      console.log(sel)
    }

  },
  mounted () {
    this.initList()
  }
}
</script> 