<template>
    <div class="new-frame-outer">
      <div class="new-frame sentence-0">
        <div class="new-frame-left">
          <div class="new-frame-image" @click="selectPicture">
            <div class="preview_zone">
              <img :src="url" alt="画像を選択">
            </div>
          </div>
          <div class="upload_zone">
            <input type="file" id="preview-image" class="input_file" ref="preview" @change="previewImage">
          </div>
          <div class="new-frame-button m-plus-rounded-1c-light">
            <a id="cancel-button" class="new-buttons btn-border-delete" @click="hideCreateFrame()"><span class="material-icons">close</span>取消</a>
            <a id="create-button" class="new-buttons btn-border-update" @click="register()"><span class="material-icons">done</span>登録</a>
          </div>
        </div>
        <div class="new-frame-right">
          <div class="new-frame-text">
            <input type="text" id="new-name" class="new-name" placeholder="名前を入力" size="100">
            <div>
              <textarea id="new-description" placeholder="説明文を入力"></textarea>
            </div>
          </div>
          <div class="new-frame-tags">
            <TagComponent ref="childComponentRef"></TagComponent>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import TagComponent from './TagComponent.vue';
import "select2";
import $ from "jquery";

export default {
  data () {
    return {
      url: ''
    }
  },
  components: {
    TagComponent
  },
  props: {
    loadMapping: {
      type: Function,
      required: true
    },
    hideCreateFrame: {
      type: Function,
      required: true
    }
  },
  mounted() {
    $('#selection').select2({
      width: '300px',
      language: 'ja',
      tags: true,
      multiple: true,
      placeholder: "選択してください",
      allowClear: true,
    });
  },
  methods: {
    previewFile: function previewFile(hoge){
      var fileData = new FileReader();
      fileData.onload = (function() {
        document.getElementById('preview').src = fileData.result;
      });
      fileData.readAsDataURL(hoge.files[0]);
    },
    previewImage() {
      let image = this.$refs.preview.files[0];
      this.url = URL.createObjectURL(image);
    },
    register: function register() {
      const fileInput = document.getElementById('preview-image');
      const file = fileInput.files[0];
      if (!file) {
          return;
      }
      const name = document.getElementById('new-name');
      const description = document.getElementById('new-description');
      const tags = this.$refs.childComponentRef.tagData();
      const tagsData = tags.map(item => ({
        tagId: item.selected ? item.id : '0',
        tagName: item.text
      }));

      const formData = new FormData();
      formData.append('file', file);
      formData.append('name', name.value);
      formData.append('description', description.value);
      formData.append('tagsData', JSON.stringify({ list: tagsData }));

      fetch('http://localhost:8080/api/v1/register', {
        method: 'POST',
        body: formData
      })
      .catch(error => {
        document.getElementById('message').textContent = 'Upload failed: ' + error;
      })
      .finally(() => {
        this.hideCreateFrame();
        this.loadMapping();
      });
    },
    asdfAsdf: function asdfAsdf() {
      this.$refs.childComponentRef.tagData();
    },
    selectPicture: function selectPicture() {
      document.getElementById('preview-image').click()
    }
  }
}
</script>

<style scoped>
.new-index-create-frame {
  margin: 32px;
  justify-content: center;
  display: flex;
}
.new-index-create-button {
  background-color: blue;
  border-radius: 8px;
  font-weight: bold;
  color: white;
  padding: 8px 16px;
}

.new-frame-outer {
  display: flex;
  justify-content: center;
}
.new-frame {
  background-color: lightpink;
  padding: 16px;
  margin: 16px 0;
  gap: 16px;
  border-radius: 8px;
  display: flex;
  display: none;
}
.new-frame-image {
  height: 250px;
  width: 250px;
  background-color: white;
  border-radius: 8px;

  display: flex;
  justify-content: center;
  align-items: center;

  img {
    max-height: 250px;
    max-width: 250px;
  }
}
.new-frame-button {
  display: flex;
}
.new-frame-text {
  background-color: white;
  padding: 16px;
  border-radius: 8px;
  height: 250px;

  textarea {
    padding: 8px;
    resize: none;
    width: 100%;
    height: 170px;
  }
}
.new-name {
  padding: 8px;
  width: 100%;
  border-bottom: 1px solid;
}
.new-frame-tags {
  margin-top: 16px;
}

.input_file {
  display: none;
}

a {
  text-decoration:none;
  font-weight: bold;
  color: white;
  padding: 4px 16px 4px 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
a.btn-border-update {
  border: 2px solid green;
  border-radius: 16px;
  background: green;
}
a.btn-border-update:hover {
  border: 2px solid green;
  color: black;
  background: white;
}
a.btn-border-delete {
  border: 2px solid red;
  border-radius: 16px;
  background: red;
}
a.btn-border-delete:hover {
  border: 2px solid red;
  color: black;
  background: white;
}

.new-frame-button {
  display: flex;
  gap: 16px;
  justify-content: center;
}
.new-buttons {
  color: white;
  margin-top: 16px;
}
</style>