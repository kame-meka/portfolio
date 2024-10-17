<template>
  <section class="home-hero footer-keep">
    <v-container fluid fill-height class="home-Service__content">
      <div class="search-frame-outer">
        <div class="search-frame">
          <div class="search-keyword-frame">
            <input type="text" id="keyword-box" class="keyword-box" placeholder="検索キーワード" size="50">
            <div class="or-checkbox">
              <input type="checkBox" id="keyword-search-or-checkbox" class="keyword-search-or-checkbox">
              <label for="keyword-search-or-checkbox">OR検索</label>
            </div>
            <select id="search-range-select" class="search-range-select" disabled>
              <option value="all">全文検索</option>
              <option value="name">名前検索</option>
            </select>
          </div>
          <div class="search-tag-frame">
            <select id="search-tag-select">
              <option v-for="option in options" :value="option.value" :key="option.value">
                {{ option.text }}
              </option>
            </select>
            <div class="or-checkbox">
              <input type="checkBox" id="tag-search-or-checkbox" class="tag-search-or-checkbox">
              <label for="tag-search-or-checkbox">OR検索</label>
            </div>
            <a @click="search" class="btn btn-border m-plus-rounded-1c-light"><span class="material-icons">search</span>検索</a>
          </div>
        </div>
      </div>
      <div class="new-index-create-frame">
        <a id="new-index-create-button" @click="displayCreateFrame()" class="btn-border-new m-plus-rounded-1c-light"><span class="material-icons">add_circle</span>新規作成</a>
      </div>
      <NewIndexComponent
        :loadMapping="loadMapping"
        :hideCreateFrame="hideCreateFrame"
      ></NewIndexComponent>
      <IndexComponent
        v-for="(item, index) in displayItem"
        v-bind:item="item"
        v-bind:index="index"
        v-bind:key="item.id"
        v-bind:options="options"
        :loadMapping="loadMapping"
      ></IndexComponent>
      <div class="under-margin"></div>
    </v-container>
  </section>
</template>

<script>
import axios from 'axios';
import IndexComponent from './IndexComponent.vue';
import NewIndexComponent from './NewIndexComponent.vue';
import "select2";
import $ from "jquery";

export default {
  data () {
    return {
      displayItem: null,
      errorMessage: "",
      loading: false,
      url: '',
      options: null
    }
  },
  created() {
    this.loadMapping();
  },
  mounted() {
    $('#search-tag-select').each((index, item) => {
      $(item).select2({
        width: '455px',
        language: 'ja',
        tags: true,
        multiple: true,
        placeholder: "  検索タグ",
      })
    });
  },
  components: {
    IndexComponent,
    NewIndexComponent
  },
  methods: {
    displayCreateFrame: function displayCreateFrame() {
      const newFrame = document.getElementsByClassName('new-frame');
      newFrame[0].style.display = 'flex';
    },
    hideCreateFrame: function hideCreateFrame() {
      const newFrame = document.getElementsByClassName('new-frame');
      newFrame[0].style.display = 'none';
    },
    loadMapping: function loadMapping() {
      console.log('loading')
      axios.get('http://localhost:8080/api/v1/load')
      .then((response) => {
        console.log(response)
        if (response.data) {
          this.displayItem = response.data.indexDataList.map(item => {
            return {
              id: item.id,
              name: item.name,
              description: item.description,
              imageUrl: this.createImageUrl(item.imageFile),
              tags: item.tags.list
            };
          });
        }
      })
      .catch((error) => {
        this.errorMessage = error;
      })
      .finally(() => {
        this.loading = false;
      });

      axios.get('http://localhost:8080/api/v1/all-tags')
      .then(response => {
        this.options = response.data.list.map(item => ({
          value: item.tagId,
          text: item.tagName
        }));
      })
      .catch(error => {
        console.error('APIリクエストに失敗しました:', error);
      });
    },
    createImageUrl: function createImageUrl(imageFile) {
      if (imageFile instanceof Blob) {
        return URL.createObjectURL(imageFile);
      } else if (typeof imageFile === 'string') {
        const byteCharacters = atob(imageFile);
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
          byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], { type: 'image/jpeg' });
        return URL.createObjectURL(blob);
      } else {
        console.error('Unsupported image file format');
      }
    },
    search: function search() {
      const keyword = document.getElementById('keyword-box').value;
      const keywordsOrCondition = document.getElementById('keyword-search-or-checkbox').checked;
      const tags = $('#search-tag-select').select2('data');
      const tagsOrCondition = document.getElementById('tag-search-or-checkbox').checked;
      const tagIds = tags.map(item => item.id);

      axios.get(`http://localhost:8080/api/v1/search?keywords=${encodeURIComponent(keyword)}&keywordsOrCondition=${keywordsOrCondition}&tagIds=${tagIds}&tagsOrCondition=${tagsOrCondition}`)
      .then((response) => {
        if (response.data) {
          this.displayItem = response.data.indexDataList.map(item => {
            return {
              id: item.id,
              name: item.name,
              description: item.description,
              imageUrl: this.createImageUrl(item.imageFile),
              tags: item.tags.list
            };
          });
        }
      })
      .catch((error) => {
        this.errorMessage = error;
      })
      .finally(() => {
        this.loading = false;
      });
    }
  }
}
</script>

<style scoped>
.home-hero {
  height: 100%;
}
.home-Service__content {
  background: url("../assets/img/home-service.jpg");
  background-size: cover;
  background-attachment: fixed;
  background-position: center center;
}
.search-frame-outer {
  display: flex;
  justify-content: center;
  margin-top: 90px;
}
.search-frame {
  background-color: lightgray;
  padding: 16px;
  border-radius: 8px;
  margin-bottom: 16px;
}
.search-keyword-frame {
  display: flex;
  justify-content: center;
  gap: 16px;
}
.search-tag-frame {
  display: flex;
  gap: 16px;
  margin-top: 16px;
}
.keyword-box {
  background-color: white;
  padding: 16px;
  border-radius: 4px;
  border: 1px solid #aaa;
}
.or-checkbox {
  display: flex;
  justify-content: center;
  align-items: center;
  label {
    margin-left: 4px;
  }
}
.search-range-select {
  background-color: white;
  border-radius: 4px;
  border: thick double;
  padding: 4px;
}
.search-button {
  background-color: blue;
  border-radius: 8px;
  margin-left: 8px;
  font-weight: bold;
  color: white;
  padding: 16px;
}
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
  justify-content: center;
}

a {
  text-decoration:none;
  font-weight: bold;
  color: white;
  padding: 0 16px 0 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
a.btn-border {
  border: 2px solid blue;
  border-radius: 16px;
  background: blue;
}
a.btn-border:hover {
  border: 2px solid blue;
  color: black;
  background: white;
}
a.btn-border-new {
  border: 2px solid black;
  border-radius: 4px;
  background: black;
  padding: 8px 16px 8px 16px;
}
a.btn-border-new:hover {
  border: 2px solid black;
  color: black;
  background: white;
  padding: 8px 16px;
}

.under-margin {
  height: 10vh;
}
</style>