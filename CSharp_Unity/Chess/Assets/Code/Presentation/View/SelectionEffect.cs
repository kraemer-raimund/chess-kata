using System;
using ChessKata.Domain;
using UnityEngine;

namespace ChessKata.Presentation
{
    public class SelectionEffect : MonoBehaviour
    {
        [SerializeField] private GameObject _visualEffect;

        private void Start() => _visualEffect.SetActive(false);

        public void OnSelectChesspiece(Position position, Func<Position, Vector3> positionToWorldMapping)
        {
            _visualEffect.transform.position = positionToWorldMapping(position);
            _visualEffect.SetActive(true);
        }

        public void OnUnselectChesspiece()
        {
            _visualEffect.SetActive(false);
        }
    }
}
