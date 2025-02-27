/*
 * Copyright 2014-2024 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

package org.jetbrains.dokka.kotlinAsJava.signatures

import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.base.signatures.All
import org.jetbrains.dokka.base.signatures.JvmSignatureUtils
import org.jetbrains.dokka.base.translators.documentables.PageContentBuilder
import org.jetbrains.dokka.links.DRI
import org.jetbrains.dokka.model.*
import org.jetbrains.dokka.model.AnnotationTarget
import org.jetbrains.dokka.model.properties.WithExtraProperties

public object JavaSignatureUtils : JvmSignatureUtils {

    private val ignoredAnnotations = setOf(
        Annotations.Annotation(DRI("kotlin.jvm", "Transient"), emptyMap()),
        Annotations.Annotation(DRI("kotlin.jvm", "Volatile"), emptyMap()),
        Annotations.Annotation(DRI("kotlin.jvm", "Transitive"), emptyMap()),
        Annotations.Annotation(DRI("kotlin.jvm", "Strictfp"), emptyMap()),
        Annotations.Annotation(DRI("kotlin.jvm", "JvmStatic"), emptyMap())
    )

    private val strategy = All
    private val listBrackets = Pair('{', '}')
    private val classExtension = ".class"

    override fun PageContentBuilder.DocumentableContentBuilder.annotationsBlock(d: AnnotationTarget) {
        annotationsBlockWithIgnored(d, ignoredAnnotations, strategy, listBrackets, classExtension)
    }

    override fun PageContentBuilder.DocumentableContentBuilder.annotationsInline(d: AnnotationTarget) {
        annotationsInlineWithIgnored(d, ignoredAnnotations, strategy, listBrackets, classExtension)
    }

    override fun <T : Documentable> WithExtraProperties<T>.modifiers(): Map<DokkaConfiguration.DokkaSourceSet, Set<ExtraModifiers.JavaOnlyModifiers>> {
        return extra[AdditionalModifiers]?.content?.entries?.associate {
            it.key to it.value.filterIsInstance<ExtraModifiers.JavaOnlyModifiers>().toSet()
        } ?: emptyMap()
    }

}
