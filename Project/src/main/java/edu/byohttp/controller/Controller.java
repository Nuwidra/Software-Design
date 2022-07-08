package edu.byohttp.controller;

import edu.byohttp.response.Response;

public interface Controller {

    Response processInputMessage(String message);
}
