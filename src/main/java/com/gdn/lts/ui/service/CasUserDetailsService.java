/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.gdn.lts.ui.service;

import com.gdn.lts.ui.outbound.feign.LtsBackendFeign;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.userdetails.AbstractCasAssertionUserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import rx.Single;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CasUserDetailsService extends AbstractCasAssertionUserDetailsService {


    @Autowired
    private LtsBackendFeign ltsBackendFeign;

    @Override
    protected UserDetails loadUserDetails(final Assertion assertion) {
        String userName = assertion.getPrincipal().getName();
        final Single<List<SimpleGrantedAuthority>> authoritiesList =
            this.ltsBackendFeign.getUserTypeByEmail(userName, userName).flatMap(response -> {
                final List<SimpleGrantedAuthority> authorities = response.getData().stream()
                    .map(userType -> new SimpleGrantedAuthority(userType))
                    .collect(Collectors.toList());

                return Single.just(authorities);
            });

        return new User(userName, "", true, true, true, true, authoritiesList.toBlocking().value());
    }
}
