<template>
   <div>
        <Table border ref="selection" :columns="columns4" :data="data1"></Table>
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
      data1: []
    }
  },
  methods: {
    handleSelectAll (status) {
      this.$refs.selection.selectAll(status)
    },
    initList () {
      let list = []
      let that = this
      service.get('/api/admin/blogs')
      .then(function (responce) {
        console.log(responce.data)
        let orglist = responce.data.blog
        orglist.forEach(item => {
          list.push({
            // title: '标题',
            title: item.title,
            label: item.label,
            abstract: item.content.substring(4, 10),
            updatetime: item.updatetime
          })
        })
        console.log(list)
        that.data1 = list
      })
    }
  },
  mounted () {
    this.initList()
  }
}
</script> 