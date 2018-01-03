-verbose
-optimizations !field/removal/writeonly,!field/marking/private,!class/merging/*,!code/allocation/variable

-keep class retrofit2.adapter.rxjava.** {*;}
-dontwarn retrofit2.adapter.rxjava.**

-keep class rx.Completable {*;}
-dontwarn rx.Completable

-keep class javax.xml.stream.** {*;}
-dontwarn javax.xml.stream.**

-keep class com.google.common.base.** {*;}
-dontwarn com.google.common.base.**

-keep class com.google.common.collect.** {*;}
-dontwarn com.google.common.collect.**

-keep class com.google.common.annotations.Beta {*;}
-dontwarn com.google.common.annotations.Beta

-keep class org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement {*;}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

-keep class java.nio.file.** {*;}
-dontwarn java.nio.file.**

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

-keep class com.google.firebase.database.** { *; }
-dontwarn com.google.firebase.database.**

-keep class org.junit.** { *; }
-dontwarn org.junit.**

-keep class org.hamcrest.** { *; }
-dontwarn org.hamcrest.**

-keep class net.arnx.jsonic.web.** { *; }
-dontwarn net.arnx.jsonic.web.**

-keep class com.google.inject.** { *; }
-dontwarn com.google.inject.**

-keep class org.seasar.framework.** { *; }
-dontwarn org.seasar.framework.**

-keep class org.slf4j.impl.** { *; }
-dontwarn org.slf4j.impl.**



-keep class javax.lang.model.util.** { *; }
-dontwarn javax.lang.model.util.**

-keep class javax.lang.model.element.** { *; }
-dontwarn javax.lang.model.element.**

-keep class javax.lang.model.type.** { *; }
-dontwarn javax.lang.model.type.**


-keep class javax.annotation.** { *; }
-dontwarn javax.annotation.**

#sun.misc
-keep class sun.misc.** { *; }
-dontwarn sun.misc.**

-dontwarn javax.**
-dontwarn autovalue.**

#com.google.auto.value.processor
-keep class com.google.auto.value.processor.** { *; }
-dontwarn com.google.auto.value.processor.**