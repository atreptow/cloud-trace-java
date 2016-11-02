// Copyright 2016 Google Inc. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.cloud.trace;

import com.google.cloud.trace.core.EndSpanOptions;
import com.google.cloud.trace.core.Labels;
import com.google.cloud.trace.core.StackTrace;
import com.google.cloud.trace.core.StartSpanOptions;
import com.google.cloud.trace.core.SpanContext;
import com.google.cloud.trace.core.TraceContext;

/**
 * An interface used for the ingestion and transmission of trace information. This is a tracer that
 * is meant to be used to instrument application code. It contains methods for starting and stopping
 * spans and adding label and stack trace annotations to spans. It provides basic context management
 * so that new spans are created as children of the span in the current context.
 *
 * @see EndSpanOptions
 * @see Labels
 * @see StackTrace
 * @see StartSpanOptions
 * @see SpanContext
 * @see Tracer
 */
public interface ManagedTracer {
  /**
   * Starts a new span and updates the current context. The new span will be a child of the span in
   * the current context.
   *
   * @param name a string that represents the name of the new span.
   * @return The {@link TraceContext} associated with the newly created span.
   */
  TraceContext startSpan(String name);

  /**
   * Starts a new span and updates the current context. The new span will be a child of the span in
   * the current context.
   *
   * @param name    a string that represents the name of the new span.
   * @param options a start span options that contains overrides for default span values.
   * @return The {@link TraceContext} associated with the newly created span.
   */
  TraceContext startSpan(String name, StartSpanOptions options);

  /**
   * Ends the current span in the provided {@link TraceContext}.
   *
   * @param traceContext The {@link TraceContext} associated with the span that will be ended.
   */
  void endSpan(TraceContext traceContext);

  /**
   * Ends the current span in the provided {@link TraceContext}.
   *
   * @param traceContext The {@link TraceContext} associated with the span that will be ended.
   * @param options an end span options that contains overrides for default span values.
   */
  void endSpan(TraceContext traceContext, EndSpanOptions options);

  /**
   * Adds label annotations to the span represented by the span context on top of the stack.
   *
   * @param traceContext The {@link TraceContext} associated with the span that should be annotated.
   * @param labels  a labels containing label annotations to add to the span.
   */
  void annotateSpan(TraceContext traceContext, Labels labels);

  /**
   * Adds a stack trace label annotation to the span represented by the span context on top of the
   * stack.
   *
   * @param traceContext The {@link TraceContext} associated with the span that should be annotated.
   * @param stackTrace a stack trace to add to the span as a label annotation.
   */
  void setStackTrace(TraceContext traceContext, StackTrace stackTrace);

  /**
   * Returns the current SpanContext.
   * @return the current SpanContext.
   */
  SpanContext getCurrentSpanContext();
}
