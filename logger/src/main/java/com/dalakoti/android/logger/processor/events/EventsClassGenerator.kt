package com.dalakoti.android.logger.processor.events

import com.dalakoti.android.logger.processor.base.BaseGenerator
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File

class EventsClassGenerator(
    private val groupedClassEvents: GroupedClassEvents,
    private val fileDir: File
): BaseGenerator {
    private lateinit var consumerClass: ClassName
    private lateinit var generatedClassName: String
    private lateinit var packageName: String

    override fun generate() {
        groupedClassEvents.getEvents().forEach {
            if (::consumerClass.isInitialized.not()) {
                consumerClass =
                    ClassName(it.value.getPackageName(), it.value.getClassSimpleName())
                generatedClassName = "${it.value.getClassSimpleName()}Events"
                packageName = it.value.getPackageName()
                return@forEach
            }
        }

        val fileBuilder = FileSpec.builder(packageName, generatedClassName)
        val classBuilder = TypeSpec.classBuilder(generatedClassName)

        classBuilder.addProperty(
            PropertySpec.builder("receiver", consumerClass)
                .addModifiers(KModifier.PRIVATE, KModifier.LATEINIT)
                .mutable(true)
                .build()
        )

        val classSpec = classBuilder
            .addType(fnGetInstance())
            .addFunction(fnRegister())
            .addFunction(fnUnregister())

        groupedClassEvents.getEvents().forEach {
            classBuilder.addFunction(fnEvents(it.value))
        }

        val classObject = classSpec.build()

        val file = fileBuilder.addType(classObject).build()
        file.writeTo(fileDir)
    }

    private fun fnRegister(): FunSpec {
        val funSpec = FunSpec.builder("register")
            .addParameter("receiver", consumerClass)
            .addStatement("this.receiver = receiver ")

        groupedClassEvents.getEvents().forEach {
            funSpec.addStatement(it.value.getMethodName() + "()")
        }

        return funSpec.build()
    }

    private fun fnUnregister(): FunSpec {
        val funSpec = FunSpec.builder("unregister")

        return funSpec.build()
    }

    private fun fnGetInstance(): TypeSpec {
        val typeSpec = TypeSpec.companionObjectBuilder()
            .addProperty(
                PropertySpec.builder("instance", ClassName(packageName, generatedClassName))
                    .addModifiers(KModifier.PRIVATE)
                    .initializer("%M()", MemberName(packageName, generatedClassName))
                    .build()
            )
            .addFunction(
                FunSpec.builder("getDefault")
                    .addStatement("return instance")
                    .build()
            )

        return typeSpec.build()
    }

    private fun fnEvents(annotation: OnEventAnnotatedMethod): FunSpec {
        val event = annotation.getMethodName()

//        val ioThread = MemberName(schedulers, "io")
//        val mainThread = MemberName(androidScheduler, "mainThread")

        val funSpec = FunSpec.builder(event)
            .addModifiers(KModifier.PRIVATE)
//            .addStatement("val disposable1 = %M.getDefault()", rxBus)
//            .addStatement(".onEvent(%S)", annotation.getEvent())
//            .addStatement(".subscribeOn(%M())", ioThread)
//            .addStatement(".observeOn(%M())", mainThread)
//            .addStatement(".subscribe(")
//            .addStatement("{receiver.${annotation.getMethodName()}()},")
//            .addStatement("{}")
//            .addStatement(")")
//            .addStatement("")
            .addStatement("println(\"code generation called at runtime\")")

        return funSpec.build()
    }
}