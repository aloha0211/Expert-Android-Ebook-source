package com.androidbook.forms;

/*
 * Unlike an IValidator this interface promisesAbility to self validate.
 * only to validate a value and tell what is wrong with it if in error.
 * 
 * Typically a Field will use multiple value validators to run through 
 * 
 * Implementing Classes:
 * RegExValidator
 * DateValidator
 * etc.
 * 
 * @see Field, IValidator, PasswordFieldRule, FormActivity
 */
public interface IValueValidator 
{
	boolean validateValue(String value);
	String getErrorMessage();
}
