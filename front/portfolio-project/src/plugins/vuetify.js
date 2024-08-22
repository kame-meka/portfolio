import { createVuetify } from 'vuetify/lib/framework';
import 'vuetify/dist/vuetify.min.css';

export default createVuetify({
  theme: {
    dark: false,
    themes: {
      light: {
        primary: '#1976D2',
        secondary: '#424242',
        accent: '#82B1FF',
        error: '#FF5252',
      },
      dark: {
        primary: '#2196F3',
        secondary: '#616161',
        accent: '#FF4081',
        error: '#FF5252',
      },
    },
  },
});