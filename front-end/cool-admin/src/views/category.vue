<template>
<div class="category-page">
    <div class="selected-nav board">
        <div class="nav-left">
            <Icon type="pricetags"></Icon>
            <span>分类</span>
        </div>
    </div>
    <div class="category-content">
    <Row type="flex" justify="center"  align="middle" class="code-row-bg">
         <Col :xs="24" :sm="24" :md="12" :lg="8" v-for="(label, index) in tags" :key="label.index">
          <div class="tag-card">
             <a href="#" class="card-header">
                 <span class="tag-icon">
                     <img src="http://chuangzaoshi.com/assets/images/D/dribbble.png" alt="">
                 </span>
                 <span class="tag-title">
                     {{label.label}}
                 </span>
             </a>
             <div class="card-content">
                 有关 {{label.label}} 的一些分享
             </div>
             <div class="card-footer">
                 <table>
                     <tr>
                        <td class="card-check" @click="chackIt(index,label.label)">查看</td>
                        <td class="card-hot">
                        <Icon type="flame"></Icon>
                        <span>{{label.blogcount}}</span>
                        </td>
                 </tr>
                 </table>
             </div>
             <div class="tag-titles">
               
             </div>
          </div>
         </Col>
    </Row>
    
    </div>
</div>
</template>
<script>
import service from '@/utils/service.js'
export default {
  data () {
    return {
      tags: '',
      tagTitle: ''
    }
  },
  methods: {
    initTags () {
      let that = this
      service.get('/api/category')
        .then(function (response) {
          // console.log(response)
          if (response.status === 200 && response.data.code === 200) {
            that.tags = response.data.category
            // console.log(that.tags)
          }
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    chackIt (i, label) {
    //   console.log(i)
      let that = this
      let title = document.getElementsByClassName('tag-titles')
    //   console.log(title[i])
      let Label = JSON.stringify(label)
    //   console.log(Label)
      title[i].style.color = 'red'
      service({
        method: 'post',
        url: '/api/label',
        data: Label,
        // dataType: 'json',
        contentType: 'application/json'
      })
      .then(function (response) {
        // console.log(response)
        if (response.status === 200 && response.data.code === 200) {
          that.tagTitle = response.data.blogbylabel
        //   console.log(that.tagTitle)
          let tagTitle = that.tagTitle
          let tagli = ''
          for (let index = 0; index < tagTitle.length; index++) {
            const tag = tagTitle[index]
            tagli += '<a ' + 'href=' + '"#/blog/' + tag.blogid + '"' + 'class="tags-title-li"' + '>' + '<li  style="list-style:none;color:#5d686f;">' + tag.title + '</li>' + '</a>'
          }
        //   console.log(tagli)
          title[i].innerHTML = tagli
        }
      })
        .catch(function (error) {
          console.log(error)
        })
    }
  },
  mounted () {
    this.initTags()
  }
}
</script>


<style scoped>
.category-page{
    max-width: 1000px;
    margin: 0px auto;
}
.selected-nav{
    padding: 16px 20px;
    border-radius: 6px;
    margin-bottom: 17px;
    margin-top: 10px;
    height: 55px;
    overflow: hidden;
}
.board{
    box-shadow: 0px 0px 6px #e3e3e3;
    background: #fff
}
.selected-nav .nav-left{
    float: left;
    position: relative;
    z-index: 10;
}
.selected-nav .nav-left span{
    font-size: 16px;
    font-weight: 700;
    color: #666;
    margin-right: 6px;
    display: inline-block;
}
.selected-nav .nav-left i{
    font-size: 20px
}
.category-content .tag-card{
        box-shadow: 0px 2px 0px rgba(170,170,170,0.1);
    background: #fff;
    margin-bottom: 20px;
    border-radius: 3px;
    margin-left: 10px;
    margin-right: 10px;
}
.category-content .tag-card .tag-titles{
    padding: 10px 18px 0;
}
.category-content .tag-card .card-header{
        overflow: hidden;
    margin-bottom: 7px;
    display: block;
    cursor: pointer;
    padding: 10px 18px 0;
    color: #333;
}
.category-content .tag-card .card-header .tag-icon{
    width: 32px;
    height: 32px;
    border-radius: 32px;
    float: left;
    display: block;
}
.category-content .tag-card .card-header .tag-icon img{
        display: block;
    width: 100%;
    height: 100%;
    border-radius: 32px;
}
.category-content .tag-card .card-header .tag-title{
        display: block;
    padding-left: 40px;
    margin-top: 6px;
    font-weight: 700;
    font-size: 15px;
    color: inherit;
}
.category-content .tag-card .card-content{
        color: #666666;
    font-size: 12px;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
    margin-bottom: 2px;
    padding: 0 18px;
}
.category-content .tag-card .card-footer{
        padding: 0 18px 8px;
}
.category-content .tag-card .card-footer i{
    font-size: 15px;
            text-align: right;
}
.category-content .tag-card .card-footer table{
    word-wrap: break-word; word-break: break-all;
}
.card-check{
    width: 90%;
    cursor: pointer;
}
.category-content .tags-title-li{
 margin-left: 10px;
    color: #5d686f;
}
.category-content .tag-card .tag-titles{
   padding-bottom: 10px
}
.category-content .tag-card .tag-titles a{
   color: #5d686f !important;
}
.category-content .tag-card .tag-titles li{
    list-style: none;
}
</style>
<style>
ul li{
    list-style: none;
};
.tags-title-li{
   color: #5d686f;
}
</style>
