/* 
 * Copyright (c) 2012, Regents of the University of Colorado 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer. 
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution. 
 * Neither the name of the University of Colorado at Boulder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. 
 */
package org.cleartk.ml.feature.transform.extractor;

import java.util.Map;

/**
 * 
 * <br>
 * Copyright (c) 2012, Regents of the University of Colorado <br>
 * All rights reserved.
 * 
 * @author Lee Becker
 */
public class CosineSimilarity implements SimilarityFunction {

  @Override
  public double distance(Map<String, Double> vector1, Map<String, Double> vector2) {
    return CosineSimilarity.dotProduct(vector1, vector2)
        / (magnitude(vector1) * magnitude(vector2));
  }

  public static double dotProduct(Map<String, Double> vector1, Map<String, Double> vector2) {
    double dot = 0.0;

    if (vector1.size() > vector2.size()) {
      Map<String, Double> tmp = vector2;
      vector2 = vector1;
      vector1 = tmp;
    }

    for (Map.Entry<String, Double> entry1 : vector1.entrySet()) {
      if (vector2.containsKey(entry1.getKey())) {
        dot += entry1.getValue() * vector2.get(entry1.getKey());
      }
    }

    return dot;
  }

  public static double magnitude(Map<String, Double> vector) {
    double mag = 0.0;
    for (double v : vector.values()) {
      mag += v * v;
    }
    return Math.sqrt(mag);
  }

}
