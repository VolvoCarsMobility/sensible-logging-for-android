package sh.vcm.sensiblelogging.processor

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import sh.vcm.sensiblelogging.channel.Channel

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class LogProcessorTest {

    private lateinit var underTest: LogProcessor

    @Before
    fun setUp() {
        underTest = LogProcessor()
    }

    @Test
    fun testname() {

    }


    private fun loggedItems() = ShadowLog.getLogs().filterNot { it.tag == "MonitoringInstr" }
}
