$(document).ready(main);

function main() {
    // js script goes here

    $('#contactForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
        },
        fields: {
            email: {
                validators: {
                    notEmpty: {
                        message: 'The email is required'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            },
            firstName: {
                validators: {
                    notEmpty: {
                        message: 'The first name is required'
                    }
                }
            },
            lastName: {
                validators: {
                    notEmpty: {
                        message: 'The last name is required'
                    }
                }
            },
            message: {
                validators: {
                    notEmpty: {
                        message: 'Your message is valued and required'
                    }
                }
            }
        }
    });
    $('#forgotPasswordForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
        },
        fields: {
            email: {
                validators: {
                    notEmpty: {
                        message: 'The email is required'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            }
        }
    });
    $('#savePasswordForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
        },
        fields: {
            password: {
                validators: {
                    notEmpty: {
                        message: 'The email is required'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: 'The password and confirm password doesn&#39;t match'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: 'The email is required'
                    },
                    identical: {
                        field: 'password',
                        message: 'The password and confirm password doesn&#39;t match'
                    }
                }
            }
        }
    });
    $('#loginForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',
        },
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: 'The username is required'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required'
                    }
                }
            }
        }
    });

    /* Signup form validation */
    $('#signupForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                validators: {
                    notEmpty: {
                        message: 'The email is required'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            },
            username: {
                validators: {
                    notEmpty: {
                        message: 'The username is required'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: 'The password and its confirm are not the same'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: 'The confirmation password is required'
                    },
                    identical: {
                        field: 'password',
                        message: 'The password and its confirm are not the same'
                    }
                }
            },
            firstName: {
                validators: {
                    notEmpty: {
                        message: 'The first name is required'
                    }
                }
            },
            lastName: {
                validators: {
                    notEmpty: {
                        message: 'The last name is required'
                    }
                }
            },
            description: {
                validators: {
                    stringLength: {
                        message: 'Post content must be less than 300 characters',
                        min: 0,
                        max: function (value, validator, $field) {
                            return 300 - (value.match(/\r/g) || []).length;
                        }
                    }
                }
            },
            phoneNumber: {
                validators: {
                    notEmpty: {
                        message: 'The phone number is required'
                    },
                    phone: {
                        country: 'country',
                        message: 'The value is not valid %s phone number'
                    }
                }
            }
        }
    })
    // Revalidate phone number when changing the country
        .on('change', '[name="country"]', function(e) {
            $('#signupForm').formValidation('revalidateField', 'phoneNumber');
        });


}
