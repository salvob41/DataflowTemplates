/*
 * Copyright (C) 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.cloud.syndeo.transforms.bigtable;

import com.google.auto.service.AutoService;
import com.google.cloud.syndeo.transforms.TypedSchemaTransformProvider;
import java.util.Collections;
import java.util.List;
import org.apache.beam.sdk.schemas.transforms.SchemaTransform;
import org.apache.beam.sdk.schemas.transforms.SchemaTransformProvider;

@AutoService(SchemaTransformProvider.class)
public class BigTableWriteSchemaTransformProvider
    extends TypedSchemaTransformProvider<BigTableWriteSchemaTransformConfiguration> {

  @Override
  public String identifier() {
    return "syndeo:schematransform:com.google.cloud:bigtable_write:v1";
  }

  @Override
  public List<String> inputCollectionNames() {
    return Collections.singletonList(BigTableIOWriteSchemaBasedTransform.INPUT_TAG);
  }

  @Override
  public List<String> outputCollectionNames() {
    return Collections.emptyList();
  }

  @Override
  public Class<BigTableWriteSchemaTransformConfiguration> configurationClass() {
    return BigTableWriteSchemaTransformConfiguration.class;
  }

  @Override
  public SchemaTransform from(BigTableWriteSchemaTransformConfiguration configuration) {
    return new BigTableIOWriteSchemaBasedTransform(
        configuration.getProjectId(),
        configuration.getInstanceId(),
        configuration.getTableId(),
        configuration.getKeyColumns(),
        configuration.getEndpoint(),
        configuration.getAppProfileId());
  }
}
