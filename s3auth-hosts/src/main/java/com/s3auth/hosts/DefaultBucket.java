/**
 * Copyright (c) 2012, s3auth.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the s3auth.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.s3auth.hosts;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import javax.validation.constraints.NotNull;

/**
 * Default implementation of {@link Bucket}.
 *
 * <p>The class is immutable and thread-safe.
 *
 * @author Yegor Bugayenko (yegor@tpc2.com)
 * @version $Id$
 * @since 0.0.1
 */
final class DefaultBucket implements Bucket {

    /**
     * The domain.
     */
    private final transient Domain domain;

    /**
     * Public ctor.
     * @param dmn The domain
     */
    public DefaultBucket(@NotNull final Domain dmn) {
        this.domain = dmn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public AmazonS3 client() {
        final AmazonS3 client = new AmazonS3Client(
            new BasicAWSCredentials(this.key(), this.secret())
        );
        client.setEndpoint(String.format("%s.amazonaws.com", this.region()));
        return client;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.domain.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.domain.name().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        return this == obj || (obj instanceof Domain
            && Domain.class.cast(obj).name().equals(this.name()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public String name() {
        return this.domain.name();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public String key() {
        return this.domain.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public String secret() {
        return this.domain.secret();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public String region() {
        return this.domain.region();
    }

}
