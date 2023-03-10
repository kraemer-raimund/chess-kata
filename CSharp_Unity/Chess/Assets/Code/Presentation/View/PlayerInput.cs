using System;
using UnityEngine;

namespace ChessKata.Presentation
{
    internal class PlayerInput : MonoBehaviour
    {
        [SerializeField] private Camera _camera;

        public event Action<GameObject> ClickedObject;

        private void Update()
        {
            if (Input.GetMouseButtonDown(0))
            {
                Vector3 mousePosition = Input.mousePosition;
                Ray ray = _camera.ScreenPointToRay(mousePosition);

                GameObject clickedObject = Physics.Raycast(ray, out RaycastHit hitInfo)
                    ? hitInfo.transform.gameObject
                    : null;

                ClickedObject?.Invoke(clickedObject);
            }
        }
    }
}
