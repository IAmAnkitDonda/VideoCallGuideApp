# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


#------------------------- Glide Progruard Rules

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule



#===================================
-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile,LineNumberTable, Annotation, EnclosingMethod
 -dontwarn android.webkit.JavascriptInterface
 -dontwarn com.googlecode.mp4parser.**
-dontwarn android.support.v4.**
#
 ##---------------Begin: proguard configuration common for all Android apps ----------
 -optimizationpasses 10
 -dontusemixedcaseclassnames
 -dontskipnonpubliclibraryclasses
 -dontskipnonpubliclibraryclassmembers
 -dontpreverify
 -verbose
# -dump class_files.txt
 -printseeds seeds.txt
 -printusage unused.txt
 -printmapping mapping.txt
 -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

 -allowaccessmodification
 -renamesourcefileattribute SourceFile
 -keepattributes SourceFile,LineNumberTable
 -repackageclasses ''

 -dontnote com.android.vending.licensing.ILicensingService
#==========================
-dontwarn android.support.v4.**
-keep class android.support.v4.* { *; }

-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-dontwarn com.squareup.okhttp.**

-keepclassmembers class livechat.videocallguide.videocall{
   public *;
}
-dontwarn org.apache.lang.**

-keep public class com.google.ads.** {
   public *;
}

-keep public class android.support.v7.widget.* { *; }
-keep public class android.support.v7.internal.widget.* { *; }
-keep public class android.support.v7.internal.view.menu.* { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}
-keep class android.support.v7.widget.RoundRectDrawable { *; }

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

-keep class com.facebook.* { *; }
-keepattributes Signature

 -keep class livechat.videocallguide.videocall.ClassThatUsesObjectAnimator { *; }

 -keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile,LineNumberTable, Annotation, EnclosingMethod
 -dontwarn android.webkit.JavascriptInterface


