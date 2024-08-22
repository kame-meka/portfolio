<template>
  <section>
    <select :id="'new-tag-select-' + index" class="new-tag-select" style="width: 75%">
      <option v-for="option in options" :value="option.value" :key="option.value">
        {{ option.text }}
      </option>
    </select>
  </section>
</template>

<script>
import "select2";
import $ from "jquery";
import axios from 'axios';

export default {
  data() {
    return {
      selectedOption: '',
      url: '',
      options: null
    }
  },
  created() {
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
  props: {
    item: {
      type: Object,
      required: false,
      default: null
    },
    index: {
      type: Number,
      required: false,
      default: 0
    }
  },
  mounted() {
    $('.new-tag-select').each((index, item) => {
      $(item).select2({
        width: '100%',
        padding: '16px',
        language: 'ja',
        tags: true,
        multiple: true,
        placeholder: "  登録タブ",
        allowClear: true,
      })
    });
  },
  methods: {
    tagData: function tagData() {
      return $('#new-tag-select-' + this.index).select2('data');
    }
  }
}
</script>

<style scoped>
.tag-select {
  padding: 16px;
}
.js-example-responsive {
  width: 75%;
}
.select2-container--default .select2-selection--multiple {
  border: 0px !important;
  border-radius: 8px;
}
</style>