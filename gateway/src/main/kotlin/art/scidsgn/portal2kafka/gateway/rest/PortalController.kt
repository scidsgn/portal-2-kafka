package art.scidsgn.portal2kafka.gateway.rest

import art.scidsgn.portal2kafka.gateway.dto.PortalEventDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class PortalController {
    @PostMapping("/portal")
    fun receiveEvent(@RequestBody event: PortalEventDto): ResponseEntity<String> {
        println(event)
        return ResponseEntity.status(HttpStatus.OK).body("")
    }
}