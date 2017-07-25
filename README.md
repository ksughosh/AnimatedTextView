# AnimatedTextView
Simple lightweight android animated typewriter type textview.

<img src = "https://media.giphy.com/media/xUn3Cke9qyJzSIpaus/giphy.gif" alt = "AnimatedTextView GIF" width = "400" />    

## Usage
This can be used with an XML layout as by setting the `app:animator` and `app:duration` flags. The `animatedTextSize` scales the text size to the specified amount in sp.
``` xml
<com.skumar.animatedtextview.AnimatedTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:animator="@animator/scale"
        app:animatedTextSize="24sp"
        app:duration="60"/>
```
Alternatively it can be used in kotlin code as:

```kotlin
  fun showAnimatedTextView(){
    val animatedText = AnimatedTextView(this)
        animatedText.text = "Hello World!"

        // Setting layout parameters is always
        // advised before adding the view to
        viewGroup.addView(animatedText)
        animatedText.startAnimate()
  }
```
By default the animation is scaling the letters.

## Download from repository
This library can be downloaded from repository by adding the following into Android studio gradle file.
```
allProjects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  compile "com.github.ksughosh:AnimatedTextView:${latest_version}"
}
```
