# AnimatedTextView
Simple lightweight android animated typewriter type textview.

<img src = "https://media.giphy.com/media/xUn3Cke9qyJzSIpaus/giphy.gif" alt = "AnimatedTextView GIF" width = "400" />    

## Usage
This can be used directly in an XML layout with settings such as the `app:animator` and `app:duration` flags. The `app:animatedTextSize` is used to set the text size to the specified amount in sp.

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

# License
The MIT License (MIT)

Copyright © SUGHOSH KRISHNA KUMAR

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
