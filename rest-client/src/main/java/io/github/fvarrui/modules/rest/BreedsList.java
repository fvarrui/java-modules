package io.github.fvarrui.modules.rest;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "message", "status" })
public class BreedsList {

	@JsonProperty("message")
	private Map<String, List<String>> message;

	@JsonProperty("status")
	private String status;

	public Map<String, List<String>> getMessage() {
		return message;
	}

	public void setMessage(Map<String, List<String>> message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BreedsList [message=" + message + ", status=" + status + "]";
	}

}