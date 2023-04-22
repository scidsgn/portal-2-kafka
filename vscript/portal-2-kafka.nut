// Console logging for Portal 2 Kafka

function PrintInfo(type) {
	printl("portal2kafka event: " + type + " on " + self.GetClassname() + " @ " + self.GetOrigin() + ", v: " + self.GetVelocity() + " on map " + GetMapName())
}

function OnLaserActivate() {
	PrintInfo("laser_activate")
}

function OnLaserDeactivate() {
	PrintInfo("laser_deactivate")
}

function OnButtonPress() {
	PrintInfo("button_press")
}

function OnButtonUnpress() {
	PrintInfo("button_unpress")
}

function OnDoorOpen() {
	PrintInfo("door_open")
}

function OnDoorClose() {
	PrintInfo("door_close")
}

function RegisterLaserEvents(entity) {
	entity.ConnectOutput("OnPowered", "OnLaserActivate")
	entity.ConnectOutput("OnUnpowered", "OnLaserDeactivate")
}

function RegisterButtonEvents(entity) {
	entity.ConnectOutput("OnPressed", "OnButtonPress")
	entity.ConnectOutput("OnUnPressed", "OnButtonUnpress")
}

function RegisterDoorEvents(entity) {
	entity.ConnectOutput("OnOpen", "OnDoorOpen")
	entity.ConnectOutput("OnClose", "OnDoorClose")
}

// LASER CATCHER //

x <- Entities.FindByClassname(null, "prop_laser_catcher")

while (x != null) {
	RegisterLaserEvents(x)
	x <- Entities.FindByClassname(x, "prop_laser_catcher")
}

// LASER RELAY //

x <- Entities.FindByClassname(null, "prop_laser_relay")

while (x != null) {
	RegisterLaserEvents(x)
	x <- Entities.FindByClassname(x, "prop_laser_relay")
}

// PEDESTAL BUTTON //

x <- Entities.FindByClassname(null, "prop_button")

while (x != null) {
	RegisterButtonEvents(x)
	x <- Entities.FindByClassname(x, "prop_button")
}

// FLOOR BUTTON //

x <- Entities.FindByClassname(null, "prop_floor_button")

while (x != null) {
	RegisterButtonEvents(x)
	x <- Entities.FindByClassname(x, "prop_floor_button")
}

// BALL BUTTON //

x <- Entities.FindByClassname(null, "prop_floor_ball_button")

while (x != null) {
	RegisterButtonEvents(x)
	x <- Entities.FindByClassname(x, "prop_floor_ball_button")
}

// CUBE BUTTON //

x <- Entities.FindByClassname(null, "prop_floor_cube_button")

while (x != null) {
	RegisterButtonEvents(x)
	x <- Entities.FindByClassname(x, "prop_floor_cube_button")
}


// CUBE BUTTON //

x <- Entities.FindByClassname(null, "prop_testchamber_door")

while (x != null) {
	RegisterDoorEvents(x)
	x <- Entities.FindByClassname(x, "prop_testchamber_door")
}
