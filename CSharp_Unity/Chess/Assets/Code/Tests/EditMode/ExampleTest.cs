using System.Collections;
using NUnit.Framework;
using UnityEngine;
using UnityEngine.TestTools;

namespace ChessKata.Tests
{
    public class ExampleTest
    {
        [Test]
        public void ShouldFail()
        {
            Assert.Fail();
        }

        [UnityTest]
        public IEnumerator ShouldFailAfter2Seconds()
        {
            yield return new WaitForSecondsRealtime(2.0f);
            Assert.Fail();
        }
    }
}
