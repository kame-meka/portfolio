<template>
  <section class="home-hero">
    <v-container fluid fill-height class="home-Service__content">
      <div class="index-frame-outer">
        <div class="index-frame">
          <div class="index-frame-left">
            <div class="index-frame-image preview_zone" @click="selectPicture">
              <img v-bind:src="imageParam" :id="'update-image-' + index" alt="">
            </div>
            <div class="upload_zone">
              <input type="file" :id="'preview-image-' + index" class="input_file" ref="preview" @change="previewImage">
            </div>
            <div class="index-frame-button m-plus-rounded-1c-light">
              <a :id="'delete-button-' + index" class="index-buttons btn btn-border-delete delete-button" @click="deleteItem"><span class="material-icons">delete</span>削除</a>
              <a :id="'update-button-' + index" class="index-buttons btn btn-border-update update-button" @click="arrangeItem"><span class="material-icons">edit</span>編集</a>
              <a :id="'save-button-' + index" class="index-buttons btn btn-border-save save-button" @click="saveItem"><span class="material-icons">edit</span>保存</a>
            </div>
          </div>
          <div class="index-frame-right">
            <div class="index-frame-text">
              <input type="text" :id="'index-name-' + index" class="index-name" v-model="nameParam" size="100" disabled>
              <div>
                <textarea :id="'index-description-' + index" v-model="descriptionParam" disabled></textarea>
              </div>
            </div>
            <div class="index-frame-tags">
              <select :id="'tag-select-' + index" class="tag-select">
                <option v-for="option in options" :value="option.value" :key="option.value">
                  {{ option.text }}
                </option>
              </select>
            </div>
          </div>
        </div>
      </div>
    </v-container>
  </section>
</template>

<script>
import "select2";
import $ from "jquery";
import axios from 'axios';

export default {
  data () {
    return {
      displayItem: null,
      errorMessage: "",
      loading: false,
      selectedOption: '',
      url: '',
      modeUpdate: false,
    }
  },
  props: {
    item: {
      type: Object,
      required: true
    },
    index: {
      type: Number,
      required: true
    },
    options: {
      type: Object,
      required: false,
      default: () => ({})
    },
    loadMapping: {
      type: Function,
      required: true
    }
  },
  created() {

  },
  mounted() {
    this.$nextTick(() => {
      const tagSelectIdName = '#tag-select-' + this.index;
      $(tagSelectIdName).select2({
        width: '100%',
        language: 'ja',
        tags: true,
        multiple: true,
        placeholder: "  登録タブ",
        url: '',
      });
      const tagIds = this.item.tags.map(tag => tag.tagId);
      $(tagSelectIdName).val(tagIds).trigger("change");
    });
  },
  computed: {
    nameParam: {
      get: function() {
        return this.item.name
      }
    },
    descriptionParam: {
      get: function() {
        return this.item.description
      }
    },
    imageParam: {
      get: function() {
        if(this.url != '') return this.url;
        return this.item.imageUrl;
      }
    },
    tags: {
      get: function() {
        return this.item.tags
      }
    },
  },
  methods: {
    previewFile: function previewFile(hoge){
      console.log('hoge' + hoge);
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
    deleteItem: function deleteItem() {
      const indexId = this.item.id
      axios.delete(`http://localhost:8080/api/v1/delete/${indexId}`)
      .then((response) => {
        if (response.data) {
          console.log(response.data.indexDataList);
        }
      })
      .catch((error) => {
        console.error(error);
        this.errorMessage = error.message;
      })
      .finally(() => {
        this.loading = false;
        this.loadMapping();
      });
    },
    tagData: function tagData() {
      return $('#tag-select').select2('data');
    },
    arrangeItem: function arrangeItem() {
      document.getElementById('update-button-' + this.index).style.display = 'none';
      document.getElementById('save-button-' + this.index).style.display = 'flex';
      document.getElementById('index-name-' + this.index).disabled = false;
      document.getElementById('index-description-' + this.index).disabled = false;
      this.modeUpdate = true;
    },
    saveItem: function saveItem() {
      document.getElementById('save-button-' + this.index).style.display = 'none';
      document.getElementById('update-button-' + this.index).style.display = 'flex';
      document.getElementById('index-name-' + this.index).disabled = true;
      document.getElementById('index-description-' + this.index).disabled = true;
      this.modeUpdate = false;

      const fileInput = document.getElementById('preview-image-' + this.index);
      let file = fileInput.files[0];
      if (!file) {
        file = null;
      }
      const id = this.item.id;
      const name = document.getElementById('index-name-' + this.index);
      const description = document.getElementById('index-description-' + this.index);
      const tags = $('#tag-select-' + this.index).select2('data');
      const tagsData = tags.map(item => ({
        tagId: item.selected ? item.id : '0',
        tagName: item.text
      }));

      const formData = new FormData();
      formData.append('id', id);
      formData.append('file', file);
      formData.append('name', name.value);
      formData.append('description', description.value);
      formData.append('tagsData', JSON.stringify({ list: tagsData }));

      fetch('http://localhost:8080/api/v1/update', {
        method: 'POST',
        body: formData
      })
      .catch(error => {
        document.getElementById('message').textContent = 'Upload failed: ' + error;
      })
      .finally(() => {
        this.loadMapping();
      });
    },
    selectPicture: function selectPicture() {
      if(!this.modeUpdate) return;
      document.getElementById('preview-image-' + this.index).click()
    }
  }
}
</script>

<style scoped>
.index-frame-outer {
  display: flex;
  justify-content: center;
}
.index-frame {
  background-color: lightgray;
  padding: 16px;
  border-radius: 8px;
  display: flex;
  gap: 16px;
}
.index-frame-image {
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

.index-frame-text {
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
.index-name {
  padding: 8px;
  width: 100%;
  border-bottom: 1px solid;
}
.index-frame-tags {
  margin-top: 16px;
}

.js-example-responsive {
  width: 75%;
}
.select2-container--default .select2-selection--multiple {
  border: none !important;
  border-radius: !important;
}

a {
  text-decoration: none;
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
a.btn-border-save {
  border: 2px solid orange;
  border-radius: 16px;
  background: orange;
}
a.btn-border-save:hover {
  border: 2px solid orange;
  color: black;
  background: white;
}

.index-frame-button {
  display: flex;
  gap: 16px;
  justify-content: center;
}
.index-buttons {
  color: white;
  margin-top: 16px;
}

.save-button {
  display: none;
}

.input_file {
  display: none;
}

</style>