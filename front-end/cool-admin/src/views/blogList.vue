<template>
   <div>
        <Table border ref="selection" :columns="columns4" :data="data1" @on-select='selectItem'></Table>
         <div style="margin-top:15px;margin-right:30px;" align="right">
        <Page :total="pageData.total" :page-size='pageData.pageSize' :current.sync="pageData.pageNum" @on-change ='changePage'></Page>
         </div>
        <Button @click="handleSelectAll(true)">全选</Button>
        <Button @click="handleSelectAll(false)">取消</Button>
        <Button @click="deleteBlog" type="error">删除</Button>
    </div>
</template>
<script>
import service from '../utils/service.js'
import * as moment from 'moment'
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
          key: 'summary'
        },
        {
          title: '更新时间',
          key: 'updatetime'
        },
        {
          title: '操作',
          key: 'action',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    // console.log(params.row.id)
                    let id = params.row.id
                    this.goEdit(id)
                  }
                }
              }, '再编辑')
            ])
          }
        }
      ],
      data1: [],
      pageData: {},
      selectItems: []
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
            summary: item.summary,
            updatetime: moment(item.updatetime).format('YYYY-MM-DD')
          })
        })
        // console.log(list)
        that.data1 = list
      })
    },
    changePage (val) {
      console.log(val)
      this.pageData.pageNum = val
      this.filterData()
    },
    filterData (fData) {
      // let data = {}
      let pageNum = this.pageData.pageNum
      let list = []
      let that = this
      // console.log(data)
      service.get('/api/admin/blogs', {
        params: {
          pageNum: pageNum
        }
      })
      .then(function (responce) {
        // console.log('change!')
        // 分页
        // that.pageData.pageSize = Number(responce.data.blog.pageSize)
        that.pageData.pageNum = Number(responce.data.blog.pageNum)
        // that.pageData.pages = Number(responce.data.blog.pages)
        let orglist = responce.data.blog.list
        orglist.forEach(item => {
          list.push({
            // title: '标题',
            id: item.blogid,
            title: item.title,
            label: item.label,
            abstract: item.summary,
            updatetime: moment(item.updatetime).format('YYYY-MM-DD')
          })
        })
        // console.log(list)
        that.data1 = list
      })
    },
    selectItem (item) {
      console.log(item)
      this.selectItems = []
      let sel = []
      for (let index = 0; index < item.length; index++) {
        const element = item[index].id
        sel.push(element)
      }
      console.log(sel)
      this.selectItems.push(sel)
      // this.selectItems = sel
    },
    deleteBlog () {
      // let blogId = this.selectItems
      let selectItems = this.selectItems
      let that = this
      let ids = selectItems.join(',')
      console.log(typeof selectItems)
      if (selectItems.length !== 0) {
        console.log(selectItems)
        this.$Modal.confirm({
          title: '提示',
          content: '真的要删除吗？？？',
          onOk: () => {
            service.delete('/api/admin/blogs', {
              params: {
                blogIds: ids
              }
            })
            .then(function (responce) {
              if (responce.data.code === 200) {
                console.log(responce)
                that.$Message.info('已删除')
              } else {
                that.$Message.info('错误')
              }
              location.reload()
              // that.$router.push('bloglist')
              // router.go(1)
            })
            // .catch(function (error) {
            //   console.log(error)
            //   that.$Message.info('错误')
            // })
          },
          onCancel: () => {
            this.$Message.info('已取消')
          }
        })
      } else {
        this.$Notice.error({
          title: '提示',
          desc: '朋友你好像没有选择吧! '
        })
      }
    },
    goEdit (id) {
      // console.log(id)
      this.$router.push({name: 'blogReEdit', params: { blogid: id }})
    }
  },
  created () {
    this.initList()
  },
  watch: {
    '$route': 'initList'
  }
}
</script> 