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
    <FormItem label="Label" prop="label">
      <Row>
           <Col  :xs="20" :sm="16" :md="12" :lg="8">
              <Select v-model="blogFormItem.label" placeholder="Select your label">
                        <Option value="js">Javascript</Option>
                        <Option value="java">Java</Option>
                        <Option value="Python">Python</Option>
              </Select>
       </Col>
      </Row>
    </FormItem>
    <FormItem label="Content" prop="content">
      <Row>
           <Col  :xs="20" :sm="16" :md="16" :lg="20">
            <quill-editor ref="myTextEditor"
                          v-model="content"
                          :config="editorOption"
                          @blur="onEditorBlur($event)"
                          @focus="onEditorFocus($event)"
                          @ready="onEditorReady($event)"
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
</div>
</template>
<script>
import { quillEditor } from 'vue-quill-editor'
import service from '../utils/service.js'
// import QS from 'querystring'

export default {
  components: {
    quillEditor
  },
  data () {
    return {
      content: '<h2>Write here</h2>',
      editorOption: {
        // something config
      },
      blogFormItem: {
        title: '',
        label: '',
        content: ''
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
        ]
      }
    }
  },
  // if you need to manually control the data synchronization, parent component needs to explicitly emit an event instead of relying on implicit binding
  // 如果需要手动控制数据同步，父组件需要显式地处理changed事件
  methods: {
    onEditorBlur (editor) {
      // console.log('editor blur!', editor)
    },
    onEditorFocus (editor) {
      // console.log('editor focus!', editor)
    },
    onEditorReady (editor) {
      // console.log('editor ready!', editor)
    },
    onEditorChange ({ editor, html, text }) {
      // console.log(html)
      this.content = html
      this.blogFormItem.content = html
    },
    // submit the form
    handleSubmit (name) {
      this.$refs[name].validate(valid => {
        console.log(this.blogFormItem)
        let that = this
        let item = this.blogFormItem
        // let data = 'title=' + item.title + '&label=' + item.label + '&content=' + item.content
        // let x = QS.stringify(item)
        let y = JSON.stringify(item)
        // console.log(x + data)
        console.log(y)
        if (valid) {
          service({
            url: '/api/admin/Blog',
            method: 'post',
            contentType: 'application/json',
            data: y
          })
          .then(function (response) {
            console.log(response)
            if (response.status === 200 && response.data.code === 201) {
              that.$Message.success('提交成功')
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
    handleReset (name) {
      this.$refs[name].resetFields()
    }
  },
  // if you need to get the current editor object, you can find the editor object like this, the $ref object is a ref attribute corresponding to the dom redefined
  // 如果你需要得到当前的editor对象来做一些事情，你可以像下面这样定义一个方法属性来获取当前的editor对象，实际上这里的$refs对应的是当前组件内所有关联了ref属性的组件元素对象
  computed: {
    editor () {
      return this.$refs.myTextEditor.quillEditor
    }
  },
  mounted () {
    // you can use current editor object to do something(editor methods)
    console.log('this is my editor', this.editor)
    // this.editor to do something...
  }
}
</script>