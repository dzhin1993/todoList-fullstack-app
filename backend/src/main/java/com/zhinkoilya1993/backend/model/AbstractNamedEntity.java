package com.zhinkoilya1993.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

	@NotBlank
	protected String name;
}
