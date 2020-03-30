import Popconfirm from '@/components/Popconfirm'
import AutoDialog from '@/components/AutoDialog'
import CropperDialog from '@/components/CropperDialog'

const components = {
  install: function (Vue) {
    Vue.component('Popconfirm', Popconfirm)
    Vue.component('AutoDialog', AutoDialog)
    Vue.component('CropperDialog', CropperDialog)
  }
}

export default components
