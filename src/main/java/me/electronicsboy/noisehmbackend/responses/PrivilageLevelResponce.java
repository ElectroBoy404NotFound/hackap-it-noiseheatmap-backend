package me.electronicsboy.noisehmbackend.responses;

import me.electronicsboy.noisehmbackend.data.PrivilegeLevel;

public class PrivilageLevelResponce {
    private PrivilegeLevel privilageLevel;

	public PrivilegeLevel getPrivilageLevel() {
		return privilageLevel;
	}

	public PrivilageLevelResponce setPrivilageLevel(PrivilegeLevel privilageLevel) {
		this.privilageLevel = privilageLevel;
		return this;
	}
}