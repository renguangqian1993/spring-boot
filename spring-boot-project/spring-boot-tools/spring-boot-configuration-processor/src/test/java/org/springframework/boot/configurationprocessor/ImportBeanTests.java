/*
 * Copyright 2012-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.configurationprocessor;

import org.junit.jupiter.api.Test;

import org.springframework.boot.configurationprocessor.metadata.ConfigurationMetadata;
import org.springframework.boot.configurationprocessor.metadata.Metadata;
import org.springframework.boot.configurationsample.ImportConfigurationPropertiesBean;
import org.springframework.boot.configurationsample.ImportConfigurationPropertiesBeans;
import org.springframework.boot.configurationsample.importbean.ImportJavaBeanConfigurationPropertiesBean;
import org.springframework.boot.configurationsample.importbean.ImportMultipleTypeConfigurationPropertiesBean;
import org.springframework.boot.configurationsample.importbean.ImportRepeatedConfigurationPropertiesBean;
import org.springframework.boot.configurationsample.importbean.ImportValueObjectConfigurationPropertiesBean;
import org.springframework.boot.configurationsample.importbean.ImportedJavaBean;
import org.springframework.boot.configurationsample.importbean.ImportedValueObject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ImportConfigurationPropertiesBean} and
 * {@link ImportConfigurationPropertiesBeans}.
 *
 * @author Phillip Webb
 */
public class ImportBeanTests extends AbstractMetadataGenerationTests {

	@Test
	void importValueObjectConfigurationPropertiesBean() {
		ConfigurationMetadata metadata = compile(ImportValueObjectConfigurationPropertiesBean.class);
		assertThat(metadata)
				.has(Metadata.withProperty("importbean.value", String.class).fromSource(ImportedValueObject.class));
	}

	@Test
	void importJavaBeanConfigurationPropertiesBean() {
		ConfigurationMetadata metadata = compile(ImportJavaBeanConfigurationPropertiesBean.class);
		assertThat(metadata)
				.has(Metadata.withProperty("importbean.name", String.class).fromSource(ImportedJavaBean.class));
	}

	@Test
	void importMultipleTypeConfigurationPropertiesBean() {
		ConfigurationMetadata metadata = compile(ImportMultipleTypeConfigurationPropertiesBean.class);
		assertThat(metadata)
				.has(Metadata.withProperty("importbean.value", String.class).fromSource(ImportedValueObject.class));
		assertThat(metadata)
				.has(Metadata.withProperty("importbean.name", String.class).fromSource(ImportedJavaBean.class));
	}

	@Test
	void importRepeatedConfigurationPropertiesBean() {
		ConfigurationMetadata metadata = compile(ImportRepeatedConfigurationPropertiesBean.class);
		assertThat(metadata).has(Metadata.withProperty("vo.value", String.class).fromSource(ImportedValueObject.class));
		assertThat(metadata).has(Metadata.withProperty("jb.name", String.class).fromSource(ImportedJavaBean.class));
	}

}
