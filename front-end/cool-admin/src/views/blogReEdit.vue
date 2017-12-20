<template>
<div class="container">
   <Form   ref="blogFormItem" :model="blogFormItem" :label-width="80" :rules="ruleValidate" >
     <FormItem label="Title:" prop="title">
       <Row>
         <Col  :xs="20" :sm="16" :md="12" :lg="8">
            <Input type="text" v-model="blogFormItem.title"></Input>
          </Col>
       </Row>
    </FormItem>
    <FormItem label="Summary:" prop="summary">
       <Row>
         <Col  :xs="20" :sm="16" :md="12" :lg="8">
            <Input type="textarea"  :rows="4" v-model="blogFormItem.summary"></Input>
          </Col>
       </Row>
    </FormItem>
    <FormItem label="Label" prop="label">
      <Row>
           <Col  :xs="20" :sm="16" :md="12" :lg="8">
              <Select v-model="blogFormItem.label" placeholder="Select your label">
                        <Option v-for="label in labels" :value="label.label" :key="label.labelid"></Option>z
              </Select>
       </Col>
      </Row>
    </FormItem>
     <FormItem label="Preview" prop="preview">
       <Row>
           <Col  :xs="20" :sm="16" :md="12" :lg="8">
                <Button type="ghost" icon="ios-cloud-upload-outline" @click="upload">Upload files</Button>
                <div v-if="blogFormItem.preview !== null">Upload file: {{blogFormItem.preview}} </div>
        </Col>
      </Row>
    </FormItem>
    <FormItem label="Content" prop="content">
      <Row>
           <Col  :xs="20" :sm="16" :md="16" :lg="20">
            <quill-editor ref="myTextEditor"
                          v-model="content"
                          :config="editorOption"
                          @change="onEditorChange($event)"
                          >
            </quill-editor>
          </Col>
      </Row>
    </FormItem>
    <FormItem>
            <Button type="primary" @click="handleSubmit('blogFormItem')">Submit</Button>
            <Button type="ghost" @click="handleReset('blogFormItem')" style="margin-left: 8px">Reset</Button>
    </FormItem>
   </Form>
    <input type="file" multiple accept="image/jpg,image/jpeg,image/png,image/gif" id='secondId' style="display: none"  @change="uploadPre">
   <form  method="post"  enctype="multipart/form-data" id="uploadFormMulti" >
       <input id="uniqueId" type="file" name="" multiple accept="image/jpg,image/jpeg,image/png,image/gif"  @change="uploadImg" style="display: none">
  </form>
</div>
</template> 
<script>
import { quillEditor } from 'vue-quill-editor'
import service from '../utils/service.js'
export default {
  components: {
    quillEditor
  },
  data () {
    return {
      content: '',
      editorOption: {
        // something config
      },
      blogFormItem: {
        title: '',
        label: '',
        content: '',
        summary: '',
        preview: '',
        thumpreview: ''
      },
      ruleValidate: {
        title: [
            { required: true, message: '别忘记写标题啊！', trigger: 'blur' }
        ],
        label: [
            { required: true, message: '别忘记选标签啊！', trigger: 'blur' }
        ],
        content: [
            { required: true, message: '别忘记写文章啊！', trigger: 'blur' }
        ],
        summary: [
            { required: true, message: '别忘记写摘要啊！', trigger: 'blur' }
        ],
        preview: [
            { required: true, message: '别忘记发预览图啊！', trigger: 'blur' }
        ]
      },
      labels: {}
    }
  },
  methods: {
    onEditorChange ({editor, html, text}) {
      this.blogFormItem.content = html
    },
    initBlog () {
      console.log(this.$route.params.blogid)
      let blogid = this.$route.params.blogid
      let that = this
      if (blogid !== null) {
        service.get('/api/admin/blog/' + blogid)
        .then(function (responce) {
          console.log(responce)
          that.blogFormItem.title = responce.data.blog.title
          that.blogFormItem.label = responce.data.blog.label
          that.blogFormItem.blogid = responce.data.blog.blogid
          that.blogFormItem.userid = responce.data.blog.userid
          that.blogFormItem.content = responce.data.blog.content
          that.blogFormItem.summary = responce.data.blog.summary
          that.blogFormItem.preview = responce.data.blog.preview
          that.blogFormItem.thumpreview = responce.data.blog.thumpreview
          that.content = responce.data.blog.content
        })
        .catch(function (error) {
          console.log(error)
        })
      }
    },
    handleReset (name) {
      this.$refs[name].resetFields()
    },
    handleSubmit (name) {
      this.$refs[name].validate(valid => {
        // console.log(this.blogFormItem)
        let that = this
        let item = this.blogFormItem
        // let y = JSON.stringify(item)
        // console.log(x + data)
        // console.log(item)
        if (valid) {
          service({
            url: '/api/admin/blog',
            method: 'put',
            contentType: 'application/json',
            data: item
          })
          .then(function (response) {
            console.log(response)
            if (response.status === 200 || response.data.code === 200) {
              that.$Message.success('提交成功')
              that.$forceUpdate()
            } else {
              that.$Message.error('提交失败了! ')
            }
          })
          .catch(function (error) {
            console.log(error)
          })
        } else {
          this.$Message.error('失败')
        }
      })
    },
    uploadImg (e) {
      let files = e.target.files[0]
      let that = this
      // let myform = document.getElementById('uploadFormMulti')
      let formData = new FormData()
      formData.append('myfile', files)
      service({
        url: '/api/admin/file',
        method: 'post',
        cache: false,
        // contentType: false,
        processData: false,
        data: formData
      })
      .then(function (response) {
        if (response.status === 200 || response.data.code === 200) {
          console.log(response.data)
          // that.addRange = that.$refs.myQuillEditor.getSelection()
          let value = response.data.msg
          value = value.indexOf('http') !== -1 ? value : 'http://' + value
          console.log(value)
          that.$refs.myTextEditor.quill.insertEmbed(that.$refs.myTextEditor.quill.getSelection().index, 'image', value)
        }
      })
      .catch(function (error) {
        console.log(error)
      })
    },
    upload () {
      let inp = document.getElementById('secondId')
      inp.click()
    },
    uploadPre (e) {
      let files = e.target.files[0]
      let that = this
      let formData = new FormData()
      formData.append('blogpreview', files)
      service({
        url: '/api/admin/blog/preview',
        method: 'post',
        cache: false,
        // contentType: false,
        processData: false,
        data: formData
      })
      .then(function (response) {
        if (response.status === 200) {
          console.log(response.data)
          // that.addRange = that.$refs.myQuillEditor.getSelection()
          let value = response.data.preview
          let value2 = response.data.thumpreview
          value = value.indexOf('http') !== -1 ? value : 'http://' + value
          value2 = value2.indexOf('http') !== -1 ? value2 : 'http://' + value2
          console.log(value)
          that.blogFormItem.preview = value
          that.blogFormItem.thumpreview = value2
        }
      })
      .catch(function (error) {
        console.log(error)
      })
    },
    needlabel () {
      let that = this
      service.get('/api/category')
        .then(function (response) {
          // console.log(response)
          if (response.status === 200 && response.data.code === 200) {
            that.labels = response.data.category
            console.log(that.labels)
          }
        })
        .catch(function (error) {
          console.log(error)
        })
    }
  },
  mounted () {
    this.initBlog()
    this.needlabel()
  },
  watch: {
    '$route': 'initBlog'
  }
}
</script>

