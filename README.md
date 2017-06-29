# GalleryPreview
Android开发之利用ViewPager实现图片的预览

```java
/**
 * 利用ViewPager图片的预览，实现类似Gallery的动画效果。
 *
 * 原理介绍：
 * 1、它的作用就是让Viewpager展示多个条目，此PageTransformer是为了在ViewPager外面展示图片。
 * 2、所以PageTransformer并没有改变VewPger的大小状态，
 *    我们在VewPer的父控件中拦截dispatchTouchEvent的方法来控制viewPger的滑动和相应点击事件，
 *    以此来实现示例中两侧的图片即可点击也可滑动的动画效果。
 */
```
![preview](https://github.com/ykmeory/GalleryPreview/blob/master/preview.gif "预览")
