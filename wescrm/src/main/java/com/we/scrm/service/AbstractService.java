package com.we.scrm.service;

import com.we.scrm.domain.AbstractEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractService <T extends AbstractEntity> {

}
