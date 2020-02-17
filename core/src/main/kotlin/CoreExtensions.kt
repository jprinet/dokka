package org.jetbrains.dokka

import org.jetbrains.dokka.plugability.ExtensionPoint
import org.jetbrains.dokka.renderers.Renderer
import org.jetbrains.dokka.renderers.OutputWriter
import org.jetbrains.dokka.resolvers.LocationProviderFactory
import org.jetbrains.dokka.transformers.descriptors.DescriptorToDocumentationTranslator
import org.jetbrains.dokka.transformers.documentation.DocumentableMerger
import org.jetbrains.dokka.transformers.documentation.DocumentationNodeTransformer
import org.jetbrains.dokka.transformers.documentation.DocumentablesToPageTranslator
import org.jetbrains.dokka.transformers.pages.PageNodeTransformer
import org.jetbrains.dokka.transformers.psi.PsiToDocumentationTranslator
import kotlin.reflect.KProperty


/**
 * Extension points declared by dokka core.
 * Default values are stored in [org.jetbrains.dokka.plugability.DefaultExtensions]
 */
object CoreExtensions {
    val descriptorToDocumentationTranslator by coreExtension<DescriptorToDocumentationTranslator>()
    val psiToDocumentationTranslator by coreExtension<PsiToDocumentationTranslator>()
    val documentableMerger by coreExtension<DocumentableMerger>()
    val documentationTransformer by coreExtension<DocumentationNodeTransformer>()
    val documentablesToPageTranslator by coreExtension<DocumentablesToPageTranslator>()
    val pageTransformer by coreExtension<PageNodeTransformer>()
    val renderer by coreExtension<Renderer>()

    val locationProviderFactory by coreExtension<LocationProviderFactory>()
    val outputWriter by coreExtension<OutputWriter>()

    private fun <T: Any> coreExtension() = object {
        operator fun provideDelegate(thisRef: CoreExtensions, property: KProperty<*>): Lazy<ExtensionPoint<T>> =
            lazy { ExtensionPoint<T>(thisRef::class.qualifiedName!!, property.name) }
    }
}